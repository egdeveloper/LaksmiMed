package org.egdeveloper.web.controllers;

import org.egdeveloper.attributes.DisplayName;
import org.egdeveloper.data.model.MedicalTest;
import org.egdeveloper.data.model.Patient;
import org.egdeveloper.service.data.IPatientService;
import org.egdeveloper.service.generators.IReportGeneratorService;
import org.egdeveloper.service.statistics.IStatisticsService;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Developer: Roman Yarnykh (egdeveloper@mail.ru)
 * Creation-Date: 29.04.2016
 * Project: LaksmiMed
 * Package: org.egdeveloper.web.controllers
 */

@Controller
public class ReportGeneratorController {

    private final IPatientService ps;

    private final IReportGeneratorService rgs;

    private final IStatisticsService s;

    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");

    @Autowired
    public ReportGeneratorController(IPatientService ps,
                                     IReportGeneratorService rgs,
                                     IStatisticsService s) {
        this.ps = ps;
        this.rgs = rgs;
        this.s = s;
    }

    @RequestMapping(value = "/generateTestReport/{testName}/{testID}")
    public void generateTestReport(@PathVariable("testName") String testName,
                                   @PathVariable("testID") Integer testID,
                                   @RequestParam("type") String mimeType,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception
    {
        OutputStream output = response.getOutputStream();
        try
        {
            Class testClazz = Class.forName("org.egdeveloper.data.model." + testName.substring(0, 1).toUpperCase() + testName.substring(1) + "Test");
            MedicalTest test = ps.findTest(testClazz, testID);
            String reportName = rgs.getReportGenerator(mimeType)
                    .buildName(String.format("%s - %s (%s)", test.getPatient().getFullName(),
                            ((DisplayName)testClazz.getAnnotation(DisplayName.class)).value(), test.getTestDate().toString(formatter)));
            response.setContentType(mimeType + "; charset=utf-8");
            response.setHeader("Content-Disposition", String.format("attachment; filename*=UTF-8''%s", URLEncoder.encode(reportName, "UTF-8").replace("+", "%20"))
            );
            rgs.generateMedicalTestReport(mimeType, test, output);
        }
        finally
        {
            if(output != null)
                output.close();
        }
    }

    @RequestMapping(value = "/patient/{patientId}/test/{testName}/dynamics/report")
    public void generateDynamicsReport(@PathVariable Integer patientId,
                                       @PathVariable String testName,
                                       @RequestParam("type") String mimeType,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Map<String, Map<LocalDate, Double>> dynamics = (Map<String, Map<LocalDate, Double>>) s.indicatorsDynamics(patientId).get(testName);
        Patient patient = ps.findPatientById(patientId);
        OutputStream out = response.getOutputStream();
        try{
            rgs.generateDynamicsReport(patient, mimeType, testName, dynamics, out);
        }
        finally {
            if(out != null)
                out.close();
        }
    }
}
