package org.egdeveloper.generators;

import org.egdeveloper.data.model.MedicalTest;
import org.egdeveloper.data.model.Patient;
import org.joda.time.LocalDate;

import java.io.OutputStream;
import java.util.Map;

/**
 * Developer: Roman Yarnykh (egdeveloper@mail.ru)
 * Creation-Date: 29.04.2016
 * Project: LaksmiMed
 * Package: org.egdeveloper.generators
 */
public interface IReportGenerator {
    void generateMedicalTestReport(MedicalTest test, OutputStream output) throws Exception;
    void generateDynamicsReport(Patient patient, String testName, Map<String, Map<LocalDate, Double>> dynamics, OutputStream output) throws Exception;
    String buildName(String prefixName);
}
