package org.egdeveloper.service.data;

import org.egdeveloper.data.dao.IPatientDAO;
import org.egdeveloper.data.model.Doctor;
import org.egdeveloper.data.model.MedicalTest;
import org.egdeveloper.data.model.Patient;
import org.egdeveloper.data.model.tempEntities.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PatientService implements IPatientService {

    @Autowired
    private IPatientDAO patientDAO;

    @Override
    public void addPatient(Doctor doctor, Patient patient){
        patientDAO.addPatient(doctor, patient);
    }

    @Override
    public void updatePatient(Patient patient){
        patientDAO.updatePatient(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Patient> findPatients(){
        return patientDAO.getPatients();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDTO> findPatients(Integer doctorID) {
        return patientDAO.getPatientsForDoctor(doctorID);
    }

    @Override
    @Transactional(readOnly = true)
    public Patient findPatientById(Integer id){
        return patientDAO.getPatientById(id);
    }

    @Override
    public boolean checkPatientExist(Patient patient) {
        return patientDAO.checkPatientExist(patient);
    }

    @Override
    public void removePatient(Integer id){
        patientDAO.removePatient(id);
    }

    @Override
    public void removePatientAndUpdateDoctor(Doctor doctor, Integer patientID) {
        patientDAO.removePatientAndUpdateDoctor(doctor, patientID);
    }

    @Override
    public void saveTest(Integer patientID, MedicalTest medicalTest) {
        patientDAO.addMedicalTest(patientID, medicalTest);
    }

    @Override
    public void saveTest(Patient patient, MedicalTest medicalTest) {
        patientDAO.addMedicalTest(patient, medicalTest);
    }

    @Override
    @Transactional(readOnly = true)
    public <T extends MedicalTest> T findTest(Class<T> medicalTestClazz, int testID) {
        return patientDAO.getMedicalTestByID(medicalTestClazz, testID);
    }

    @Override
    @Transactional(readOnly = true)
    public <T extends MedicalTest> List<T> findTests(Class<T> medicalTestClass) {
        return patientDAO.retrieveMedicalTestsByType(medicalTestClass);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicalTest> findTests() {
        return patientDAO.retrieveAllMedicalTests();
    }
}
