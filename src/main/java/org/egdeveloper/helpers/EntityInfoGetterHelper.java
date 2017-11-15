package org.egdeveloper.helpers;

import org.egdeveloper.attributes.StatVariable;
import org.egdeveloper.data.model.MedicalTest;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Developer: Roman Yarnykh (egdeveloper@mail.ru)
 * Creation-Date: 30.04.2016
 * Project: LaksmiMed
 * Package: org.egdeveloper.helpers
 */

@Component("entityInfoGetterHelper")
public class EntityInfoGetterHelper {

    private Map<Class<? extends MedicalTest>, List<Field>> indicatorsMap = new HashMap<>();
    private List<Class<? extends MedicalTest>> testTypes = new ArrayList<>();

    private Reflections reflections = new Reflections("org.egdeveloper.data.model");

    {
        Set<Class<? extends MedicalTest>> testClazzes = reflections.getSubTypesOf(MedicalTest.class);
        for (Class<? extends MedicalTest> testClazz : testClazzes) {
            List<Field> indicators = new ArrayList<>();
            for (Field field : testClazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(StatVariable.class)) {
                    field.setAccessible(true);
                    indicators.add(field);
                }
            }
            indicatorsMap.put(testClazz, indicators);
        }
        testTypes.addAll(testClazzes);
    }

    public List<Field> getAllIndicators(Class medicalTestClazz){
        return indicatorsMap.get(medicalTestClazz);
    }

    public List<Class<? extends MedicalTest>> getTestTypes()
    {
        return testTypes;
    }
}
