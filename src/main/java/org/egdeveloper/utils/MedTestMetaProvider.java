package org.egdeveloper.utils;

import org.egdeveloper.attributes.DisplayName;
import org.egdeveloper.attributes.EntityID;
import org.egdeveloper.attributes.IndicatorNorm;
import org.egdeveloper.data.model.MedicalTest;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Developer: egdeveloper
 * Creation-Date: 15.08.16
 */

@Component
public class MedTestMetaProvider implements IModelMetaProvider<Map<String, MedTestMeta>> {

    private Map<String, MedTestMeta> metaInfo;

    @PostConstruct
    public void init(){
        metaInfo = new HashMap<>();
        Reflections reflections = new Reflections("org.egdeveloper.data.model");
        for(Class testClazz : reflections.getSubTypesOf(MedicalTest.class)){
            MedTestMeta testMeta = new MedTestMeta();
            testMeta.setTestClass(testClazz);
            testMeta.setName(testClazz.getSimpleName());
            testMeta.setEntityID(((EntityID)testClazz.getAnnotation(EntityID.class)).value());
            for(Field ind : testClazz.getDeclaredFields()){
                if(ind.isAnnotationPresent(IndicatorNorm.class)){
                    IndicatorMeta indMeta = new IndicatorMeta();
                    indMeta.setDisplayName(ind.getAnnotation(DisplayName.class).value());
                    Column columnAnn = ind.getAnnotation(Column.class);
                    if(columnAnn != null)
                        indMeta.setName(columnAnn.name());
                    else
                        indMeta.setName(ind.getName());
                    IndicatorNorm norm = ind.getAnnotation(IndicatorNorm.class);
                    indMeta.setMin(norm.minCritical());
                    indMeta.setMax(norm.maxCritical());
                    testMeta.getIndicators().add(indMeta);
                }
            }
            metaInfo.put(testMeta.getEntityID(), testMeta);
        }
    }

    @Override
    public Map<String, MedTestMeta> provideMeta() {
        return metaInfo;
    }
}
