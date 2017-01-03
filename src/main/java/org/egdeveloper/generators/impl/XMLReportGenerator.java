package org.egdeveloper.generators.impl;


import org.egdeveloper.attributes.DisplayName;
import org.egdeveloper.data.model.MedicalTest;
import org.egdeveloper.data.model.Patient;
import org.egdeveloper.generators.IReportGenerator;
import org.egdeveloper.helpers.EntityInfoGetterHelper;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Developer: Roman Yarnykh (egdeveloper@mail.ru)
 * Creation-Date: 03.05.2016
 * Project: LaksmiMed
 * Package: org.egdeveloper.generators
 */

@Component
public class XMLReportGenerator implements IReportGenerator {

    private DocumentBuilder documentBuilder;
    private Transformer transformer;

    @Autowired
    private EntityInfoGetterHelper entityInfoGetterHelper;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");

    @PostConstruct
    public void initBean() throws Exception{
        documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        transformer = TransformerFactory.newInstance().newTransformer();
    }


    @Override
    public void generateMedicalTestReport(MedicalTest test, OutputStream output) throws Exception {

        Patient patient = test.getPatient();
        List<Field> indicators = entityInfoGetterHelper.getAllIndicators(test.getClass());

        Document document = documentBuilder.newDocument();

        //root
        Element testReportNode = document.createElement("testReport");

        //patient
        Element patientNode = document.createElement("patient");

        Element patientInfoNode = document.createElement("fullName");
        patientInfoNode.appendChild(document.createTextNode(patient.getFullName()));
        patientNode.appendChild(patientInfoNode); //fullName

        patientInfoNode = document.createElement("gender");
        patientInfoNode.appendChild(document.createTextNode(patient.getGender().gender2String()));
        patientNode.appendChild(patientInfoNode); //gender

        patientInfoNode = document.createElement("birthdate");
        patientInfoNode.appendChild(document.createTextNode(patient.getBirthdate().toString(dateTimeFormatter)));
        patientNode.appendChild(patientInfoNode); //birthdate

        patientInfoNode = document.createElement("address");
        patientInfoNode.appendChild(document.createTextNode(patient.getCountry() + ", "
                + patient.getPostIndex() + ", "
                + patient.getRegion() + ", "
                + patient.getCity() + ", "
                + patient.getAddress()));
        patientNode.appendChild(patientInfoNode); //address

        testReportNode.appendChild(patientNode);

        //test
        Element testNode = document.createElement("test");
        Element testInfoNode = document.createElement("testDate");
        testInfoNode.appendChild(document.createTextNode(test.getTestDate().toString(dateTimeFormatter)));
        testNode.appendChild(testInfoNode);

        Element indicatorsNode = document.createElement("indicators");

        //indicators
        for(Field indicator : indicators){
            Element indicatorNode = document.createElement("indicator");
            indicatorNode.setAttribute("name", indicator.getAnnotation(DisplayName.class).value());
            Object indicator_value = indicator.get(test);
            if(indicator_value == null) {
                indicatorNode.setAttribute("isNull", "true");
                indicatorNode.setAttribute("value", "");
            }
            else {
                indicatorNode.setAttribute("isNull", "false");
                indicatorNode.setAttribute("value", indicator_value.toString());
            }
            indicatorsNode.appendChild(indicatorNode);
        }
        testNode.appendChild(indicatorsNode);
        testReportNode.appendChild(testNode);

        document.appendChild(testReportNode);

        StreamResult streamResult = new StreamResult(output);
        transformer.transform(new DOMSource(document), streamResult);
    }

    @Override
    public void generateDynamicsReport(Patient patient, String testName, Map<String, Map<LocalDate, Double>> dynamics, OutputStream output) throws Exception {

    }

    @Override
    public String buildName(String prefixName) {
        return prefixName + ".xml";
    }
}
