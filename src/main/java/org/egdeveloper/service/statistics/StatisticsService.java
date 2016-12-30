package org.egdeveloper.service.statistics;

import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Longs;
import lombok.Getter;
import lombok.Setter;
import org.egdeveloper.attributes.DisplayName;
import org.egdeveloper.attributes.EntityID;
import org.egdeveloper.attributes.MedTest;
import org.egdeveloper.attributes.StatVariable;
import org.egdeveloper.data.model.MedicalTest;
import org.egdeveloper.data.model.Patient;
import org.egdeveloper.data.model.customTypes.Gender;
import org.egdeveloper.data.model.customTypes.StoneXRay;
import org.egdeveloper.data.model.customTypes.TreatmentNumber;
import org.egdeveloper.helpers.EntityInfoGetterHelper;
import org.egdeveloper.service.data.IPatientService;
import org.egdeveloper.utils.IndicatorMeta;
import org.egdeveloper.utils.MedTestMeta;
import org.egdeveloper.utils.MedTestMetaProvider;
import org.hibernate.*;
import org.hibernate.transform.Transformers;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.min;
import static java.lang.Math.sqrt;

/**
 * Developer: egdeveloper
 * Creation-Date: 13.08.16
 */

@Transactional(readOnly = true)
@Service
public class StatisticsService implements IStatisticsService{

    @Autowired
    private IPatientService patientService;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MedTestMetaProvider testMetaProvider;

    private DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    private Map<Class, Pair<Method, Method[]>> indGetters = new HashMap<>();
    private Map<Class, List<Field>> indFields = new HashMap<>();
    private Map<String, Field> testsGetters = new HashMap<>();
    private Map<Class, Field> testFields = new HashMap<>();

    @Autowired
    private EntityInfoGetterHelper ei;

    @PostConstruct
    public void initService(){
        Method[] testGetters = __getMethodsAnnotatedBy(Patient.class, MedTest.class);
        for(Method testGetter : testGetters){
            Class testClazz = __innerGenericType(testGetter);
            indGetters.put(testClazz, new Pair<>(testGetter, __indicatorsGetters(testClazz)));
        }
        for(Field testField : Arrays.asList(Patient.class.getDeclaredFields()).stream()
                .filter(f -> f.isAnnotationPresent(MedTest.class))
                .collect(Collectors.toList()))
        {
            testField.setAccessible(true);
            Class<? extends MedicalTest> testClazz = (Class<? extends MedicalTest>) (((ParameterizedType)testField.getGenericType())
                    .getActualTypeArguments()[0]);
            testsGetters.put(testClazz.getAnnotation(EntityID.class).value(), testField);
            indFields.put(testClazz, __testIndicators(testClazz));
        }
        Arrays.stream(Patient.class.getDeclaredFields()).filter(f -> f.isAnnotationPresent(MedTest.class)).forEach(f -> {
            f.setAccessible(true);
            testFields.put(f.getAnnotation(MedTest.class).value(), f);
        });
    }

    @Override
    public Map<Object, Object> indicatorsDynamics(Integer patientId) throws IllegalAccessException, InvocationTargetException {
        Map<Object, Object> indicatorsStat = new HashMap<>();

        //Get patient by id
        Patient patient = patientService.findPatientById(patientId);

        for(Class testClass : ei.getTestTypes()){
            Map<String, Map<LocalDate, Double>> indicators = new HashMap<>();
            Set<MedicalTest> tests = (Set<MedicalTest>)testFields.get(testClass).get(patient);
            for(Field ind : ei.getAllIndicators(testClass)){
                ind.setAccessible(true);
                Map<LocalDate, Double> indicatorSamples = new HashMap<>();
                for(MedicalTest test : tests){
                    indicatorSamples.put(test.getTestDate(), (Double) ind.get(test));
                }
                indicators.put(ind.getAnnotation(DisplayName.class).value(), indicatorSamples);
            }
//            for(Method indGetter : indGetters.get(testClass).second){
//                Map<LocalDate, Double> indicatorSamples = new HashMap<>();
//                for(MedicalTest test : tests){
//                    indicatorSamples.put(test.getTestDate(), (Double) indGetter.invoke(test));
//                }
//                indicators.put(indGetter.getAnnotation(DisplayName.class).value(), indicatorSamples);
//            }
            //indicatorsStat.put(((DisplayName)testClass.getAnnotation(DisplayName.class)).value(), indicators);
            indicatorsStat.put(((EntityID)testClass.getAnnotation(EntityID.class)).value(), indicators);
        }
        indicatorsStat.put("testNorms", indNorms());
        return indicatorsStat;
    }

    @Override
    public Map patientStatistics() {
        LocalDate now = LocalDate.now();
        Map result = new HashMap<>();
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("select gender, birthdate, diseaseDuration, region, badHabits, associatedDisease from Patient");
            List<Object[]> rows = query.list();
            result.put("volume", rows.size());
            for (Object[] row : rows) {
                LocalDate birthdate = LocalDate.parse(row[1].toString(), formatter);
                __collectStatistics(result, "gender", Gender.valueOf(row[0].toString()).gender2String());
                __collectStatistics(result, "age", (Years.yearsBetween(birthdate, now).getYears() / 10) * 10);
                __collectStatistics(result, "duration", row[2].toString());
                __collectStatistics(result, "region", row[3].toString());
                __collectStatistics(result, "badHabits", row[4].toString());
                __collectStatistics(result, "associatedDisease", row[5].toString());
            }
            tx.commit();
            return result;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map stoneComponentsStatistics(TreatmentNumber treatmentNumber) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Query query = session.createSQLQuery("CALL collect_StCsDevsStat(:treatment_number)")
                    .setParameter("treatment_number", treatmentNumber.toString())
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            Map stCsDevsStat_ = new HashMap<>();
            for (Object row_ : query.list()) {
                Map mapRow_ = (Map) row_;
                Map stoneTypeStat_ = new HashMap<>();
                for (Object column_ : mapRow_.keySet()) {
                    if (column_.toString().endsWith("_total")) {
                        Map indicatorStat_ = new HashMap<>();
                        indicatorStat_.put("volume", mapRow_.get(column_.toString()));
                        indicatorStat_.put("deviations", mapRow_.get(column_.toString().replace("_total", "_devs")));
                        stoneTypeStat_.put(column_.toString().replace("_total", ""), indicatorStat_);
                    }
                }
                stCsDevsStat_.put(mapRow_.get("stoneType").toString(), stoneTypeStat_);
            }
            tx.commit();
            return stCsDevsStat_;
        } catch (Exception exception) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Map diseaseAndStoneStatistics(TreatmentNumber treatmentNumber) {
        Transaction tx = null;
        try(Session session = sessionFactory.openSession()){
            tx = session.beginTransaction();
            Query query = session.createSQLQuery("CALL collect_StoneAndDiseaseStat(:treatmentNumber)")
                    .setParameter("treatmentNumber", treatmentNumber.toString());
            List<Object[]> rows = query.list();
            Map<String, DiseaseStat> result = new HashMap<>();
            for(Object[] row : rows){

                if(!row[0].toString().contains(";")){
                    __collectDiseaseStatistics(result, StoneXRay.valueOf(row[1].toString()), row[0].toString());
                }
                else{
                    String[] diseases = row[0].toString().split(";");
                    for(String disease : diseases){
                        __collectDiseaseStatistics(result, StoneXRay.valueOf(row[1].toString()), disease);
                    }
                }
            }
            __calculateDiseaseCorr(result);
            tx.commit();
            return result;
        }
        catch (Exception e){
            if(tx != null)
                tx.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map indDevsStoneStatistics(TreatmentNumber treatmentNumber, String testID) {
        Transaction tx = null;
        try(Session session = sessionFactory.openSession()){
            MedTestMeta testMeta = testMetaProvider.provideMeta().get(testID);
            tx = session.beginTransaction();
            String queryStr = String.format("SELECT test.*, stone.xray FROM %s as test INNER JOIN StoneInVitroTest as stone " +
                    "ON test.patient_id = stone.patient_id " +
                    "WHERE test.treatmentNumber = '%s' AND stone.treatmentNumber = '%s'", testMeta.getName(), treatmentNumber.toString(), treatmentNumber.toString());
            Query query = session.createSQLQuery(queryStr).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            List<Map<String, Object>> rows = query.list();
            Map res = new HashMap<>();
//            res.put("volume", rows.size());
            for(Map<String, Object> row : rows){
                StoneXRay stone = StoneXRay.valueOf(row.get("xray").toString());
                for(IndicatorMeta indicator : testMeta.getIndicators()){
                    Double value = (Double) row.get(indicator.getName());
                    __collectIndDevsStatistics(res, indicator.getDisplayName(), stone, value, indicator);
                }
            }
            for(IndicatorMeta indicator : testMeta.getIndicators()){
                IndicatorStat indStat = (IndicatorStat) res.get(indicator.getDisplayName());
                __indStoneCorr(indStat, rows.size());
                __calculateIndStoneCorr(indStat);
            }
            tx.commit();
            return res;
        }
        catch (Exception e){
            if(tx != null)
                tx.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map indNorms() {
        final Map norms = new HashMap();
        Map<String, MedTestMeta> testsMeta = testMetaProvider.provideMeta();
        testsMeta.forEach((t, m) -> {
            norms.put(t, m.getIndicators()
                    .stream()
                    .collect(Collectors.toMap(IndicatorMeta::getDisplayName,
                            (i) -> ImmutableMap.of("min", i.getMin(), "max", i.getMax()))));
        });
        return norms;
    }

    private void __collectDiseaseStatistics(Map<String, DiseaseStat> statistics, StoneXRay stone, String disease){
        if(disease.contains("нет") || disease.isEmpty())
            return;
        if(!statistics.containsKey(disease))
            statistics.put(disease, new DiseaseStat());
        DiseaseStat diseaseStat = statistics.get(disease);
        switch (stone){
            case OXALATES:
                diseaseStat.oxalates++;
                break;
            case PHOSPHATES:
                diseaseStat.phosphates++;
                break;
            case URATES:
                diseaseStat.urates++;
                break;
            case OXALATES_PHOSPHATES:
                diseaseStat.oxalates++;
                diseaseStat.phosphates++;
                break;
            case OXALATES_URATES:
                diseaseStat.oxalates++;
                diseaseStat.urates++;
                break;
            case URATES_PHOSPHATES:
                diseaseStat.urates++;
                diseaseStat.phosphates++;
                break;
        }
        statistics.put(disease, diseaseStat);
    }

    private void __calculateDiseaseCorr(Map<String, DiseaseStat> diseaseStat){
        Map<String, Integer> d2i = new HashMap<>();
        int i = 0;
        for(String disease : diseaseStat.keySet())
            d2i.put(disease, i++);
        int[][] table = new int[d2i.size() + 1][4];
        for(String disease : diseaseStat.keySet()){
            DiseaseStat ds = diseaseStat.get(disease);
            table[d2i.get(disease)][0] = ds.oxalates;
            table[d2i.get(disease)][1] = ds.phosphates;
            table[d2i.get(disease)][2] = ds.urates;
            table[d2i.get(disease)][3] = ds.oxalates + ds.phosphates + ds.urates;
        }
        for(int j = 0; j < table.length - 1; j++){
            table[table.length - 1][0] += table[j][0];
            table[table.length - 1][1] += table[j][1];
            table[table.length - 1][2] += table[j][2];
        }
        for(int j = 0; j < table.length - 1; j++){
            table[table.length - 1][3] += table[j][3];
        }
        for(int j = 0; j < 4; j++){
            table[table.length - 1][3] += table[table.length - 1][j];
        }
        for(String disease : diseaseStat.keySet()){
            //oxalates
            int[] t = new int[4];
            t[0] = table[d2i.get(disease)][0];
            t[1] = table[d2i.get(disease)][3] - t[0];
            t[2] = table[table.length - 1][0] - t[0];
            t[3] = table[table.length - 1][3] - t[1] - t[2] + t[0];
            diseaseStat.get(disease).oxalates_corr = __phiCorr(t) / sqrt(table[table.length - 1][3]);
            //phosphates

            t[0] = table[d2i.get(disease)][1];
            t[1] = table[d2i.get(disease)][3] - t[0];
            t[2] = table[table.length - 1][1] - t[0];
            t[3] = table[table.length - 1][3] - t[1] - t[2] + t[0];
            diseaseStat.get(disease).phosphates_corr = __phiCorr(t) / sqrt(table[table.length - 1][3]);
            //urates
            t[0] = table[d2i.get(disease)][2];
            t[1] = table[d2i.get(disease)][3] - t[0];
            t[2] = table[table.length - 1][2] - t[0];
            t[3] = table[table.length - 1][3] - t[1] - t[2] + t[0];
            diseaseStat.get(disease).urates_corr = __phiCorr(t) / sqrt(table[table.length - 1][3]);
        }
    }

    private double __phiCorr(int[] table){
        return (table[0] * table[3] - table[1] * table[2]) / sqrt(table[0] * table[1] * table[2] * table[3]);
    }

    private void __collectStatistics(Map statistics, String feature, Object obj){
        Map fs;

        if(!statistics.containsKey(feature)) {
            fs = new HashMap<>();
            statistics.put(feature, fs);
        }
        else
            fs = (Map) statistics.get(feature);

        if(fs.containsKey(obj))
            fs.put(obj, (Integer)fs.get(obj) + 1);
        else
           fs.put(obj, 1);
    }

    private void __collectIndDevsStatistics(Map statistics, String indicator, StoneXRay stone, Double value, IndicatorMeta indicatorMeta){
        if(!statistics.containsKey(indicator))
            statistics.put(indicator, new IndicatorStat());
        IndicatorStat ind_stat = (IndicatorStat) statistics.get(indicator);
        switch (stone){
            case OXALATES:
//                if(value == null){
////                    ind_stat.empty.oxalates++;
//
//                }
                if((value != null) && (value < indicatorMeta.getMin() || value > indicatorMeta.getMax())){
                    ind_stat.devs.oxalates++;
                }
                else{
                    ind_stat.normal.oxalates++;
                }
                break;
            case PHOSPHATES:
//                if(value == null){
////                    ind_stat.empty.phosphates++;
//
//                }
                if((value != null) && (value < indicatorMeta.getMin() || value > indicatorMeta.getMax())){
                    ind_stat.devs.phosphates++;
                }
                else{
                    ind_stat.normal.phosphates++;
                }
                break;
            case URATES:
//                if(value == null){
////                    ind_stat.empty.urates++;
//
//                }
                if((value != null) && (value < indicatorMeta.getMin() || value > indicatorMeta.getMax())){
                    ind_stat.devs.urates++;
                }
                else{
                    ind_stat.normal.urates++;
                }
                break;
            case OXALATES_PHOSPHATES:
//                if(value == null){
////                    ind_stat.empty.oxalates++;
////                    ind_stat.empty.phosphates++;
//
//                }
                if((value != null) && (value < indicatorMeta.getMin() || value > indicatorMeta.getMax())){
                    ind_stat.devs.oxalates++;
                    ind_stat.devs.phosphates++;
                }
                else{
                    ind_stat.normal.oxalates++;
                    ind_stat.normal.phosphates++;
                }
                break;
            case OXALATES_URATES:
//                if(value == null){
////                    ind_stat.empty.oxalates++;
////                    ind_stat.empty.urates++;
//                }
                if((value != null) && (value < indicatorMeta.getMin() || value > indicatorMeta.getMax())){
                    ind_stat.devs.oxalates++;
                    ind_stat.devs.urates++;
                }
                else{
                    ind_stat.normal.oxalates++;
                    ind_stat.normal.urates++;
                }
                break;
            case URATES_PHOSPHATES:
//                if(value == null){
////                    ind_stat.empty.urates++;
////                    ind_stat.empty.phosphates++;
//
//                }
                if((value != null) && (value < indicatorMeta.getMin() || value > indicatorMeta.getMax())){
                    ind_stat.devs.urates++;
                    ind_stat.devs.phosphates++;
                }
                else{
                    ind_stat.normal.urates++;
                    ind_stat.normal.phosphates++;
                }
                break;
        }
        statistics.put(indicator, ind_stat);
    }
    private void __indStoneCorr(IndicatorStat ind_stat, int total){
        long[][] table = new long[2][3];
        table[0][0] = ind_stat.normal.oxalates;
        table[0][1] = ind_stat.normal.phosphates;
        table[0][2] = ind_stat.normal.urates;
        table[1][0] = ind_stat.devs.oxalates;
        table[1][1] = ind_stat.devs.phosphates;
        table[1][2] = ind_stat.devs.urates;
//        table[2][0] = ind_stat.empty.oxalates;
//        table[2][1] = ind_stat.empty.phosphates;
//        table[2][2] = ind_stat.empty.urates;
        Pair<Double, Integer> cramersV = __cramersV(table);
        Pair<Double, Integer> pearsonC = __pearsonC(table);
        ind_stat.corr = cramersV.first;
        ind_stat.pearson_c = pearsonC.first;
        ind_stat.df = cramersV.second;
    }

    private Pair<double[], long[]> __chiSqTable(long[][] table){
        int r = table.length;
        int c = table[0].length;
        int[] rt = new int[r];
        int[] ct = new int[c];
        for(int i = 0; i < r; i++){
            rt[i] = 0;
            for(int j = 0; j < c; j++) rt[i] += table[i][j];
        }
        for(int j = 0; j < c; j++){
            ct[j] = 0;
            for(int i = 0; i < r; i++) ct[j] += table[i][j];
        }
        int total = 0;
        for(int i = 0; i < r; i++)
            total += rt[i];
        List<Long> obs = new ArrayList<>();
        List<Double> exp = new ArrayList<>();
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(table[i][j] == 0)
                    continue;
//                if(table[i][j] == 0){
//                    obs.add(0L);
//                    exp.add(0.00001);
//                }
                obs.add(table[i][j]);
                exp.add((rt[i] * ct[j] * 1.0) / (total * 1.0));
            }
        }
        return new Pair<>(Doubles.toArray(exp), Longs.toArray(obs));
    }

    private Pair<Double, Integer> __chiSquare(long[][] table){
        int r = table.length;
        int c = table[0].length;
        int[] rt = new int[r];
        int[] ct = new int[c];
        for(int i = 0; i < r; i++){
            rt[i] = 0;
            for(int j = 0; j < c; j++) rt[i] += table[i][j];
        }
        for(int j = 0; j < c; j++){
            ct[j] = 0;
            for(int i = 0; i < r; i++) ct[j] += table[i][j];
        }
        int total = 0;
        for(int i = 0; i < r; i++)
            total += rt[i];
        double chisq = 0.0;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                double expected = (rt[i] * ct[j] * 1.0) / (total * 1.0);
                if(expected != 0.0)
                    chisq += (table[i][j] - expected) * (table[i][j] - expected) / expected;
            }
        }
        return new Pair<>(chisq, total);
    }

    private Pair<Double, Integer> __cramersV(long[][] table){
        int r = table.length;
        int c = table[0].length;
        Pair<Double, Integer> res = __chiSquare(table);
        return new Pair<>(sqrt(res.first / (res.second * min(r - 1, c - 1))), (r - 1) * (c - 1));
    }

    private Pair<Double, Integer> __pearsonC(long[][] table){
        int r = table.length;
        int c = table[0].length;
        Pair<Double, Integer> res = __chiSquare(table);
        return new Pair<>(sqrt(res.first / (res.first + res.second)), (r - 1) * (c - 1));
    }

    private void __calculateIndStoneCorr(IndicatorStat ind_stat)
    {
        long[][] isct = new long[2][2];

        //Indicator - Oxalates correlation
        isct[0][0] = ind_stat.normal.oxalates;
        isct[1][0] = ind_stat.devs.oxalates;
        isct[0][1] = ind_stat.normal.phosphates + ind_stat.normal.urates;
        isct[1][1] = ind_stat.devs.phosphates + ind_stat.devs.urates;
//        isct[2][0] = ind_stat.empty.oxalates;
//        isct[2][1] = ind_stat.empty.phosphates + ind_stat.empty.urates;
        ind_stat.stone_corr.oxalates_corr = __cramersV(isct).first;

        //Indicator - Phosphate correlation
        isct[0][0] = ind_stat.normal.phosphates;
        isct[1][0] = ind_stat.devs.phosphates;
        isct[0][1] = ind_stat.normal.oxalates + ind_stat.normal.urates;
        isct[1][1] = ind_stat.devs.oxalates + ind_stat.devs.urates;
//        isct[2][0] = ind_stat.empty.phosphates;
//        isct[2][1] = ind_stat.empty.oxalates + ind_stat.empty.urates;
        ind_stat.stone_corr.phosphates_corr = __cramersV(isct).first;

        //Indicator - Urates correlation
        isct[0][0] = ind_stat.normal.urates;
        isct[1][0] = ind_stat.devs.urates;
        isct[0][1] = ind_stat.normal.oxalates + ind_stat.normal.phosphates;
        isct[1][1] = ind_stat.devs.oxalates + ind_stat.devs.phosphates;
//        isct[2][0] = ind_stat.empty.urates;
//        isct[2][1] = ind_stat.empty.oxalates + ind_stat.empty.phosphates;
        ind_stat.stone_corr.urates_corr = __cramersV(isct).first;
    }

    private <T extends MedicalTest> List<Field> __testIndicators(Class<T> testClass){
        List<Field> inds = new ArrayList<>();
        for(Field field : testClass.getDeclaredFields()){
            if(field.isAnnotationPresent(StatVariable.class)){
                field.setAccessible(true);
                inds.add(field);
            }
        }
        return inds;
    }

    private Method[] __indicatorsGetters(Class test){
        return __getMethodsAnnotatedBy(test, StatVariable.class);
    }

    private Class __innerGenericType(Method method){
        return (Class) ((ParameterizedType)method.getGenericReturnType()).getActualTypeArguments()[0];
    }

    private Method[] __getMethodsAnnotatedBy(final Class clazz, final Class<? extends Annotation> annotation){
        List<Method> methods = new ArrayList<>();
        for(Method m : clazz.getDeclaredMethods()){
            if(m.isAnnotationPresent(annotation))
                methods.add(m);
        }
        Method[] selMethods = new Method[methods.size()];
        methods.toArray(selMethods);
        return selMethods;
    }
}


class IndicatorStat{

    @Getter @Setter
    StoneStat normal = new StoneStat();

    @Getter @Setter
    StoneStat devs = new StoneStat();

    @Getter @Setter
    StoneCorr stone_corr = new StoneCorr();

    @Getter @Setter
    double corr;

    @Getter @Setter
    double pearson_c;

    @Getter @Setter
    int df;
}

class StoneStat{

    @Getter @Setter
    int oxalates;

    @Getter @Setter
    int phosphates;

    @Getter @Setter
    int urates;
}

class StoneCorr{

    @Getter @Setter
    double oxalates_corr;

    @Getter @Setter
    double phosphates_corr;

    @Getter @Setter
    double urates_corr;
}

class DiseaseStat{

    @Getter @Setter
    public double oxalates_corr;

    @Getter @Setter
    public double phosphates_corr;

    @Getter @Setter
    public double urates_corr;

    @Getter @Setter
    double corr;

    @Getter @Setter
    int oxalates;

    @Getter @Setter
    int phosphates;

    @Getter @Setter
    int urates;
}

class Pair<T1, T2>{

    Pair(T1 first, T2 second){
        this.first = first;
        this.second = second;
    }

    public T1 first;

    public T2 second;
}

class Tuple<T1, T2, T3>{

    Tuple(T1 first, T2 second, T3 third){
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T1 first;

    public T2 second;

    public T3 third;
}