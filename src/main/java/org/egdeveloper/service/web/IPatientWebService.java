package org.egdeveloper.service.web;

import org.egdeveloper.data.model.MedicalTest;
import org.egdeveloper.data.model.Patient;
import org.egdeveloper.data.model.tempEntities.PatientDTO;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Developer: egdeveloper
 * Creation-Date: 10.08.16
 */
public interface IPatientWebService{
    Patient addPatient(Integer doctorID, Patient patient);
    Patient updatePatient(Patient patient);
    List<PatientDTO> findPatients(Integer doctorID);
    Patient findPatientById(Integer id);
    Patient removePatient(Integer patientId);
    MedicalTest saveTest(Integer patientID, MedicalTest test) throws IOException;
    Collection<MedicalTest> findTests(Integer patientID);
}
