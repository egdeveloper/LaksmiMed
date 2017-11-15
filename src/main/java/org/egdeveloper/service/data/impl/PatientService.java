package org.egdeveloper.service.data.impl;

import org.egdeveloper.data.dao.IPatientDAO;
import org.egdeveloper.data.model.Doctor;
import org.egdeveloper.data.model.MedicalTest;
import org.egdeveloper.data.model.Patient;
import org.egdeveloper.data.model.tempEntities.PatientDTO;
import org.egdeveloper.service.data.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PatientService implements IPatientService {

    private final IPatientDAO patientDAO;

    @Autowired
    public PatientService(IPatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Override
    public void savePatient(Doctor doctor, Patient patient){
        patientDAO.savePatient(doctor, patient);
    }

    @Override
    public void updatePatient(Patient patient){
        patientDAO.updatePatient(patient);
    }

    @Override
    public List<Patient> findPatients(){
        return patientDAO.findPatients();
    }

    @Override
    public List<PatientDTO> findPatients(Integer doctorID) {
        return patientDAO.findPatientsForDoctor(doctorID);
    }

    @Override
    public Patient findPatientById(Integer id){
        return patientDAO.findPatientById(id);
    }

    @Override
    public boolean checkPatientExist(Patient patient) {
        return patientDAO.checkPatientExist(patient);
    }

    @Override
    public void deletePatient(Integer id){
        patientDAO.deletePatient(id);
    }

    @Override
    public void deletePatient(Doctor doctor, Integer patientID) {
        patientDAO.deletePatient(doctor, patientID);
    }

    @Override
    public void saveTest(Integer patientID, MedicalTest medicalTest) {
        patientDAO.saveTest(patientID, medicalTest);
    }

    @Override
    public void saveTest(Patient patient, MedicalTest medicalTest) {
        patientDAO.saveTest(patient, medicalTest);
    }

    @Override
    public <T extends MedicalTest> T findTest(Class<T> medicalTestClazz, int testID) {
        return patientDAO.findTestByID(medicalTestClazz, testID);
    }

    @Override
    public <T extends MedicalTest> List<T> findTests(Class<T> medicalTestClass) {
        return patientDAO.findTestsByType(medicalTestClass);
    }

    @Override
    public List<MedicalTest> findTests() {
        return patientDAO.findTests();
    }
}
