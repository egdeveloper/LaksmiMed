package org.egdeveloper.service.web;

import org.egdeveloper.data.model.customTypes.TreatmentNumber;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
