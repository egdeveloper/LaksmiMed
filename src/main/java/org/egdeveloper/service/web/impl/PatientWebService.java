package org.egdeveloper.service.web.impl;

import org.egdeveloper.data.model.Doctor;
import org.egdeveloper.data.model.MedicalTest;
import org.egdeveloper.data.model.Patient;
import org.egdeveloper.data.model.tempEntities.PatientDTO;
import org.egdeveloper.service.data.IDoctorService;
import org.egdeveloper.service.data.IPatientService;
import org.egdeveloper.service.web.IPatientWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Developer: egdeveloper
 * Creation-Date: 10.08.16
 */

@RestController
@RequestMapping("/rest-service")
public class PatientWebService implements IPatientWebService {

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IDoctorService doctorService;

    @Override
    @RequestMapping(value = "/doctor/{doctorID}/patient",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    public Patient addPatient(@PathVariable Integer doctorID, @RequestBody Patient patient) {
        Doctor doctor = doctorService.findDoctorByID(doctorID);
        if(doctor == null)
            throw new RuntimeException(String.format("Doctor with id = %d not found!", doctorID));
        patientService.savePatient(doctor, patient);
        return patient;
    }

    @Override
    @RequestMapping(value = "/patient",
            method = RequestMethod.PUT,
            consumes = "application/json",
            produces = "application/json"
    )
    public Patient updatePatient(@RequestBody Patient patient) {
        if(!patientService.checkPatientExist(patient))
            throw new RuntimeException("Patient not found!");
        Patient currentPatient = patientService.findPatientById(patient.getId());
        patient.setDoctor(currentPatient.getDoctor());
        patient.setBioChemTests(currentPatient.getBioChemTests());
        patient.setCommonBloodTests(currentPatient.getCommonBloodTests());
        patient.setCommonUreaTests(currentPatient.getCommonUreaTests());
        patient.setDailyExcreationTests(currentPatient.getDailyExcreationTests());
        patient.setStoneInVitroTests(currentPatient.getStoneInVitroTests());
        patient.setStoneInVivoTests(currentPatient.getStoneInVivoTests());
        patient.setTitrationTests(currentPatient.getTitrationTests());
        patient.setUreaColorTests(currentPatient.getUreaColorTests());
        patientService.updatePatient(patient);
        return patient;
    }

    @Override
    @RequestMapping(value = "/doctor/{doctorID}/patients", produces = "application/json")
    public List<PatientDTO> findPatients(@PathVariable Integer doctorID) {
        Doctor doctor = doctorService.findDoctorByID(doctorID);
        if(doctor == null)
            throw new RuntimeException(String.format("Doctor with id = %d not found!", doctorID));
        return patientService.findPatients(doctorID);
    }

    @Override
    @RequestMapping(value = "/patient/{patientID}", produces = "application/json")
    public Patient findPatientById(@PathVariable Integer patientID) {
        Patient patient = patientService.findPatientById(patientID);
        if(patient == null)
            throw new RuntimeException(String.format("Patient with id = %d not found", patientID));
        return patient;
    }

    @Override
    @RequestMapping(value = "/patient/{patientID}", method = RequestMethod.DELETE, produces = "application/json")
    public Patient removePatient(@PathVariable Integer patientID) {
        Patient patient = patientService.findPatientById(patientID);
        if(patient == null)
            throw new RuntimeException(String.format("Patient with id = %d not found", patientID));
        patientService.deletePatient(patientID);
        return patient;
    }

    @Override
    @RequestMapping(value = "/patient/{patientID}/test", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public MedicalTest saveTest(@PathVariable Integer patientID, @RequestBody MedicalTest test) throws IOException{
        Patient patient = patientService.findPatientById(patientID);
        if (patient == null)
            throw new RuntimeException(String.format("Patient with id = %d not found", patientID));
        patientService.saveTest(patient, test);
        return test;
    }

    @Override
    @RequestMapping(value = "/patient/{patientID}/tests", produces = "application/json")
    public Collection<MedicalTest> findTests(@PathVariable Integer patientID) {
        Patient patient = patientService.findPatientById(patientID);
        if(patient == null)
            throw new RuntimeException(String.format("Patient with id = %d not found", patientID));
        return patient.findTests();
    }
}
