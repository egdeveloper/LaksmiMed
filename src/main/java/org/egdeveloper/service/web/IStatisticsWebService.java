package org.egdeveloper.service.web;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Developer: egdeveloper
 * Creation-Date: 13.08.16
 */
public interface IStatisticsWebService {
    Map indicatorDynamics(Integer patientId) throws InvocationTargetException, IllegalAccessException;
    Map patientStatistics();
    Map stoneComponentsStatistics(String treatmentNumber);
    Map diseaseAndStoneStatistics(String treatmentNumber);
    Map indDevsStoneStatistics(String testID, String treatmentNumber);
    Map indNorms();
}
