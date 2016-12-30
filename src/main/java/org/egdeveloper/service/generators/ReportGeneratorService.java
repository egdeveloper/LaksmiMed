package org.egdeveloper.service.generators;

import org.egdeveloper.data.model.MedicalTest;
import org.egdeveloper.data.model.Patient;
import org.egdeveloper.generators.IReportGenerator;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Developer: Roman Yarnykh (egdeveloper@mail.ru)
 * Creation-Date: 28.04.2016
 * Project: LaksmiMed
 * Package: org.egdeveloper.service
 */

@Service
public class ReportGeneratorService implements IReportGeneratorService {

    private Map<String, IReportGenerator> reportGenerators_ = new HashMap<>();

    @Override
    public void generateMedicalTestReport(String mimeType, MedicalTest test, OutputStream output) throws Exception {
        reportGenerators_.get(mimeType).generateMedicalTestReport(test, output);
    }

    @Override
    public void generateDynamicsReport(Patient patient, String mimeType, String testName, Map<String, Map<LocalDate, Double>> dynamics, OutputStream output) throws Exception {
        reportGenerators_.get(mimeType).generateDynamicsReport(patient, testName, dynamics, output);
    }

    @Override
    public IReportGenerator getReportGenerator(String mimeType) {
        return reportGenerators_.get(mimeType);
    }

    @Override
    public void registerGenerator(String mimeType, IReportGenerator generator) {
        reportGenerators_.put(mimeType, generator);
    }
}
