package org.egdeveloper.data.dao;

import org.egdeveloper.data.model.Doctor;
import org.egdeveloper.data.model.MedicalTest;
import org.egdeveloper.data.model.Patient;
import org.egdeveloper.data.model.tempEntities.PatientDTO;

import java.util.List;

/**
 * Common interface of DAO for manipulating with patient data
 * @author Yarnykh Roman egdeveloper@mail.ru
 * @version 1.0
 * @since 05.01.2016 (5th January 2016)
 * @see org.egdeveloper.data.dao.PatientDAO
 */
public interface IPatientDAO {

    /**
     * Add new patient record in local database
     * @param doctor doctor's infor
     * @param patient patient record
     */
    void addPatient(Doctor doctor, Patient patient);

    /**
     * List of all existing patient records
     * @return list of patient records
     */
    List<Patient> getPatients();

    List<PatientDTO> getPatientsForDoctor(Integer doctorID);
    /**
     * Get unique patient record
     * @param id unique patient id
     * @return Patient instance
     */
    Patient getPatientById(Integer id);

    /**
     * Remove existing patient record
     * @param id id of existing patient record
     */
    void removePatient(Integer id);

    /**
     * Get patient entry from doctor record, delete it and update doctor record
     * @param doctor doctor record
     * @param patientId patientID
     */
    void removePatientAndUpdateDoctor(Doctor doctor, Integer patientId);

    /**
     * Update patient info
     * @param patient patient record
     */
    void updatePatient(Patient patient);

    /**
     * Check is patient exists
     * @param patient patient
     * @return check result
     */
    boolean checkPatientExist(Patient patient);

    /**
     * Add medical test to existing patient record
     * @param patientID patient id
     * @param test test
     */
    void addMedicalTest(Integer patientID, MedicalTest test);

    /**
     * Add medical test to existing patient record
     * @param patient patient
     * @param test test
     */
    void addMedicalTest(Patient patient, MedicalTest test);

    /**
     * Retrieve all medical tests by defined test type
     * @param medicalTestClass medical test type
     * @param <T> medical test generic type
     * @return list of medical tests
     */
    <T> List<T> retrieveMedicalTestsByType(Class<T> medicalTestClass);

    <T extends MedicalTest> T getMedicalTestByID(Class<T> medicalTestClass, int testID);

    /**
     * Retrieve all medical tests from database
     * @return list of medical tests
     */
    List<MedicalTest> retrieveAllMedicalTests();
}
