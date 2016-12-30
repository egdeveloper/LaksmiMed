package org.egdeveloper.service.web;

import org.egdeveloper.data.model.customTypes.TreatmentNumber;
import org.egdeveloper.helpers.EntityInfoGetterHelper;
import org.egdeveloper.service.statistics.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Developer: egdeveloper
 * Creation-Date: 13.08.16
 */

@RestController
@RequestMapping("/stat-service")
public class StatisticsWebService implements IStatisticsWebService{

    @Autowired
    private IStatisticsService service;

    @Override
    @RequestMapping(value = "/indicators/dynamics/{patientId}")
    public Map indicatorDynamics(@PathVariable Integer patientId) throws InvocationTargetException, IllegalAccessException {
        return service.indicatorsDynamics(patientId);
    }

    @Override
    @RequestMapping(value = "/patients/statistics")
    public Map patientStatistics() {
        return service.patientStatistics();
    }

    @Override
    @RequestMapping(value = "/tests/stone-components/{treatmentNumber}")
    public Map stoneComponentsStatistics(@PathVariable String treatmentNumber) {
        return service.stoneComponentsStatistics(TreatmentNumber.valueOf(treatmentNumber));
    }

    @Override
    @RequestMapping(value = "/tests/disease-stone/{treatmentNumber}")
    public Map diseaseAndStoneStatistics(@PathVariable String treatmentNumber) {
        return service.diseaseAndStoneStatistics(TreatmentNumber.valueOf(treatmentNumber));
    }

    @Override
    @RequestMapping(value = "/tests/ind-devs-stone/{testID}/{treatmentNumber}")
    public Map indDevsStoneStatistics(@PathVariable String testID, @PathVariable String treatmentNumber){
        return service.indDevsStoneStatistics(TreatmentNumber.valueOf(treatmentNumber), testID);
    }

    @Override
    @RequestMapping(value = "/tests/norms")
    public Map indNorms(){
        return service.indNorms();
    }
}
