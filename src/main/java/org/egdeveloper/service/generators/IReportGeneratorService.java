package org.egdeveloper.service.generators;

import org.egdeveloper.data.model.MedicalTest;
import org.egdeveloper.data.model.Patient;
import org.egdeveloper.generators.IReportGenerator;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Developer: Roman Yarnykh (egdeveloper@mail.ru)
 * Creation-Date: 28.04.2016
 * Project: LaksmiMed
 * Package: org.egdeveloper.service
 */
public interface IReportGeneratorService {
    void generateMedicalTestReport(String mimeType, MedicalTest test, OutputStream output) throws Exception;
    void generateDynamicsReport(Patient patient, String mimeType, String testName, Map<String, Map<LocalDate, Double>> dynamics, OutputStream output) throws Exception;
    IReportGenerator getReportGenerator(String mimeType);
    void registerGenerator(String mineType, IReportGenerator generator);
}
