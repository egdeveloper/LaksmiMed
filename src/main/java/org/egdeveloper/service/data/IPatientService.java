package org.egdeveloper.service.data;

import org.egdeveloper.data.model.Doctor;
import org.egdeveloper.data.model.MedicalTest;
import org.egdeveloper.data.model.Patient;
import org.egdeveloper.data.model.tempEntities.PatientDTO;

import java.util.List;


public interface IPatientService {
    void savePatient(Doctor doctor, Patient patient);
    void updatePatient(Patient patient);
    List<Patient> findPatients();
    List<PatientDTO> findPatients(Integer doctorID);
    Patient findPatientById(Integer id);
    boolean checkPatientExist(Patient patient);
    void deletePatient(Integer patientId);
    void deletePatient(Doctor doctor, Integer patientID);
    void saveTest(Integer patientID, MedicalTest medicalTest);
    void saveTest(Patient patient, MedicalTest medicalTest);
    <T extends MedicalTest> List<T> findTests(Class<T> medicalTestClass);
    <T extends MedicalTest> T findTest(Class<T> medicalTestClazz, int testID);
    List<MedicalTest> findTests();
}
