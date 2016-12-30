package org.egdeveloper.data.dao;

import org.egdeveloper.data.model.*;
import org.egdeveloper.data.model.tempEntities.PatientDTO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.TypeLocatorImpl;
import org.hibernate.transform.Transformers;
import org.hibernate.type.EnumType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Repository("patientDAO")
public class PatientDAO implements IPatientDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPatient(Doctor doctor, Patient patient){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            patient.setDoctor(doctor);
            session.save(patient);
            List<Patient> patients = doctor.getPatients();
            patients.add(patient);
            doctor.setPatients(patients);
            session.saveOrUpdate(doctor);
            tx.commit();
        }
        catch (Exception exception){
            if(tx != null)
                tx.rollback();
            throw new RuntimeException(exception);
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<Patient> getPatients(){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List<Patient> patients = (List<Patient>) session.createQuery("from Patient").list();
            tx.commit();
            return patients;
        }
        catch (Exception exception){
            if(tx != null)
                tx.rollback();
            throw new RuntimeException(exception);
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<PatientDTO> getPatientsForDoctor(Integer doctorID)
    {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
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
            List<PatientDTO> patients = query.list();
            tx.commit();
            return patients;
        }
        catch (Exception exception){
            if(tx != null)
                tx.rollback();
            throw new RuntimeException(exception);
        }
        finally {
            session.close();
        }
    }

    @Override
    public Patient getPatientById(Integer id){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Query query = sessionFactory.getCurrentSession().createQuery("from Patient where id = :id");
            query.setParameter("id", id);
            Patient patient = (Patient)query.uniqueResult();
            tx.commit();
            return patient;
        }
        catch (Exception exception){
            if(tx != null)
                tx.rollback();
            throw new RuntimeException(exception);
        }
        finally {
            session.close();
        }
    }

    @Override
    public void updatePatient(Patient patient) {
        /*
        sessionFactory.getCurrentSession().update(patient);
         */
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(patient);
            tx.commit();
        }
        catch (Exception exception){
            if(tx != null)
                tx.rollback();
            throw new RuntimeException(exception);
        }
        finally {
            session.close();
        }
    }

    @Override
    public boolean checkPatientExist(Patient patient) {
        return !(patient == null || getPatientById(patient.getId()) == null);
    }

    @Override
    public void removePatient(Integer patientId){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Patient patient = (Patient) session.load(Patient.class, patientId);
            if(patient != null){
                Doctor doctor = patient.getDoctor();
                doctor.getPatients().remove(patient);
                session.update(doctor);
                //patient.setDoctor(null);
                //session.delete(patient);
            }
            tx.commit();
        }
        catch (Exception exception){
            if(tx != null)
                tx.rollback();
            throw new RuntimeException(exception);
        }
        finally {
            session.close();
        }
    }

    @Override
    public void removePatientAndUpdateDoctor(Doctor doctor, Integer patientId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Patient patient = (Patient)session.get(Patient.class, patientId);
            if(patient != null){
                doctor.getPatients().remove(patient);
                patient.setDoctor(null);
                //session.update(patient);
                //session.merge(doctor);
                //patient.setDoctor(null);
                session.delete(patient);
            }
            tx.commit();
        }
        catch (Exception exception){
            if(tx != null)
                tx.rollback();
            throw new RuntimeException(exception);
        }
        finally {
            session.close();
        }
    }

    @Override
    public void addMedicalTest(Integer patientID, MedicalTest test) {
        Patient patient = this.getPatientById(patientID);
        this.addMedicalTest(patient, test);
    }

    @Override
    public void addMedicalTest(Patient patient, MedicalTest test) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            patient.addTest(test);
            test.setPatient(patient);
            session.update(patient);
            tx.commit();
        }
        catch (Exception exception){
            if(tx != null)
                tx.rollback();
            throw new RuntimeException();
        }
        finally {
            session.close();
        }
    }

    @Override
    public <T> List<T> retrieveMedicalTestsByType(Class<T> medicalTestClass){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("from " + medicalTestClass.getSimpleName());
            List<T> testList = (List<T>)query.list();
            tx.commit();
            return testList;
        }
        catch (Exception exception){
            if(tx != null)
                tx.rollback();
            throw new RuntimeException(exception);
        }
        finally {
            session.close();
        }
    }

    @Override
    public <T extends MedicalTest> T getMedicalTestByID(Class<T> medicalTestClazz, int testID) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            T medicalTest = (T) session.get(medicalTestClazz, testID);
            tx.commit();
            return medicalTest;
        }
        catch (Exception exception){
            if(tx != null)
                tx.rollback();
            throw new RuntimeException(exception);
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<MedicalTest> retrieveAllMedicalTests() {
        List<MedicalTest> medicalTests = new ArrayList<>();
        medicalTests.addAll(retrieveMedicalTestsByType(BioChemTest.class));
        medicalTests.addAll(retrieveMedicalTestsByType(CommonBloodTest.class));
        medicalTests.addAll(retrieveMedicalTestsByType(CommonUreaTest.class));
        medicalTests.addAll(retrieveMedicalTestsByType(DailyExcreationTest.class));
        medicalTests.addAll(retrieveMedicalTestsByType(StoneInVitroTest.class));
        medicalTests.addAll(retrieveMedicalTestsByType(StoneInVivoTest.class));
        medicalTests.addAll(retrieveMedicalTestsByType(TitrationTest.class));
        medicalTests.addAll(retrieveMedicalTestsByType(UreaColorTest.class));
        return medicalTests;
    }
}
