package org.egdeveloper.data.dao.impl;

import org.egdeveloper.data.dao.IPatientDAO;
import org.egdeveloper.data.model.*;
import org.egdeveloper.data.model.tempEntities.PatientDTO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.TypeLocatorImpl;
import org.hibernate.transform.Transformers;
import org.hibernate.type.EnumType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Transactional
@Repository
public class PatientDAO implements IPatientDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PatientDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void savePatient(Doctor doctor, Patient patient){
        try (Session session = sessionFactory.openSession()) {
            patient.setDoctor(doctor);
            session.save(patient);
            List<Patient> patients = doctor.getPatients();
            patients.add(patient);
            doctor.setPatients(patients);
            session.saveOrUpdate(doctor);
        } 
    }

    @Override
    @Transactional(readOnly = true)
    public List<Patient> findPatients(){
        try (Session session = sessionFactory.openSession()) {
            return (List<Patient>) session.createQuery("from Patient").list();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDTO> findPatientsForDoctor(Integer doctorID)
    {
        try (Session session = sessionFactory.openSession()) {
            Properties properties = new Properties();
            properties.put("enumClass", "org.egdeveloper.data.model.customTypes.Gender");
            properties.put("useNamed", "true");
            properties.put("type", "12");
            org.hibernate.type.Type genderType = new TypeLocatorImpl(new TypeResolver()).custom(EnumType.class, properties);
            Query query = session.createSQLQuery("SELECT id, fullName, gender, cardNumber, country, postIndex, region, city, address from Patient where doctor_id = :doctor_id")
                    .addScalar("id", IntegerType.INSTANCE)
                    .addScalar("fullName", StringType.INSTANCE)
                    .addScalar("gender", genderType)
                    .addScalar("cardNumber", StringType.INSTANCE)
                    .addScalar("country", StringType.INSTANCE)
                    .addScalar("postIndex", StringType.INSTANCE)
                    .addScalar("region", StringType.INSTANCE)
                    .addScalar("city", StringType.INSTANCE)
                    .addScalar("address", StringType.INSTANCE)
                    .setParameter("doctor_id", doctorID)
                    .setResultTransformer(Transformers.aliasToBean(PatientDTO.class));

            return (List<PatientDTO>) query.list();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Patient findPatientById(Integer id){
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Patient where id = :id");
            query.setParameter("id", id);
            return (Patient) query.uniqueResult();
        }
    }

    @Override
    public void updatePatient(Patient patient) {
        try (Session session = sessionFactory.openSession()) {
            session.update(patient);
        }
    }

    @Override
    public boolean checkPatientExist(Patient patient) {
        return !(patient == null || findPatientById(patient.getId()) == null);
    }

    @Override
    public void deletePatient(Integer patientId){
        try (Session session = sessionFactory.openSession()) {
            Patient patient = session.load(Patient.class, patientId);
            if (patient != null) {
                Doctor doctor = patient.getDoctor();
                doctor.getPatients().remove(patient);
                session.update(doctor);
                //patient.setDoctor(null);
                //session.delete(patient);
            }
        }
    }

    @Override
    public void deletePatient(Doctor doctor, Integer patientId) {
        try (Session session = sessionFactory.openSession()) {
            Patient patient = session.get(Patient.class, patientId);
            if (patient != null) {
                doctor.getPatients().remove(patient);
                patient.setDoctor(null);
                //session.update(patient);
                //session.merge(doctor);
                //patient.setDoctor(null);
                session.delete(patient);
            }
        }
    }

    @Override
    public void saveTest(Integer patientID, MedicalTest test) {
        Patient patient = this.findPatientById(patientID);
        this.saveTest(patient, test);
    }

    @Override
    public void saveTest(Patient patient, MedicalTest test) {
        try (Session session = sessionFactory.openSession()) {
            patient.addTest(test);
            test.setPatient(patient);
            session.update(patient);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public <T> List<T> findTestsByType(Class<T> medicalTestClass){
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from " + medicalTestClass.getSimpleName());
            return (List<T>) query.list();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public <T extends MedicalTest> T findTestByID(Class<T> medicalTestClazz, int testID) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(medicalTestClazz, testID);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicalTest> findTests() {
        List<MedicalTest> medicalTests = new ArrayList<>();
        medicalTests.addAll(findTestsByType(BioChemTest.class));
        medicalTests.addAll(findTestsByType(CommonBloodTest.class));
        medicalTests.addAll(findTestsByType(CommonUreaTest.class));
        medicalTests.addAll(findTestsByType(DailyExcreationTest.class));
        medicalTests.addAll(findTestsByType(StoneInVitroTest.class));
        medicalTests.addAll(findTestsByType(StoneInVivoTest.class));
        medicalTests.addAll(findTestsByType(TitrationTest.class));
        medicalTests.addAll(findTestsByType(UreaColorTest.class));
        return medicalTests;
    }
}
