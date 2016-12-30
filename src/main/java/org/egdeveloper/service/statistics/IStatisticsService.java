package org.egdeveloper.service.statistics;

import org.egdeveloper.data.model.customTypes.TreatmentNumber;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Developer: egdeveloper
 * Creation-Date: 13.08.16
 */
public interface IStatisticsService {
    Map<Object, Object> indicatorsDynamics(Integer patientId) throws IllegalAccessException, InvocationTargetException;
    Map patientStatistics();
    Map stoneComponentsStatistics(TreatmentNumber treatmentNumber);
    Map diseaseAndStoneStatistics(TreatmentNumber treatmentNumber);
    Map indDevsStoneStatistics(TreatmentNumber treatmentNumber, String testName);
    Map indNorms();
}
