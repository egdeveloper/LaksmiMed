package org.egdeveloper.data.dao;

import org.egdeveloper.data.model.Doctor;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("doctorDAO")
public class DoctorDAO implements IDoctorDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addDoctor(Doctor doctor){
        /*
        sessionFactory.getCurrentSession().save(doctor);
        */
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(doctor);
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
    public void updateDoctorInfo(Doctor doctor) {
        /*
        sessionFactory.getCurrentSession().update(doctor);
         */
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(doctor);
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
    public List<Doctor> getDoctors(){
        /*
        sessionFactory.getCurrentSession().createQuery("from Doctor").list();
         */
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Doctor> doctors = new ArrayList<>();
        try{
            tx = session.beginTransaction();
            doctors = (List<Doctor>)session.createQuery("from Doctor").list();
            tx.commit();
            return doctors;
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
    public Doctor getDoctorByLogin(String login){
        /*
        Query query = sessionFactory.getCurrentSession().createQuery("from Doctor where login = :login and password = :password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        List doctorList = query.list();
        if(doctorList == null || doctorList.size() <= 0)
            return null;
        else
            return (Doctor)doctorList.get(0);
            */
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Doctor doctor = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Doctor where login = :login");
            query.setParameter("login", login);
            List doctorList = query.list();
            if(doctorList != null && doctorList.size() > 0) {
                doctor = (Doctor) doctorList.get(0);
                Hibernate.initialize(doctor.getPatients());
            }
            tx.commit();
            return doctor;
        }
        catch (Exception exception) {
            if(tx != null)
                tx.rollback();
            throw new RuntimeException(exception);
        }
        finally {
            session.close();
        }
    }

    @Override
    public Doctor getDoctorById(Integer id) {
        /*
        Query query = sessionFactory.getCurrentSession().createQuery("from Doctor where id = :id");
        query.setParameter("id", id);
        return (Doctor)query.uniqueResult();
        */
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Doctor doctor;
        try{
            tx = session.beginTransaction();
            Query query = sessionFactory.getCurrentSession().createQuery("from Doctor where id = :id");
            query.setParameter("id", id);
            doctor = (Doctor)query.uniqueResult();
            if(doctor != null){
                Hibernate.initialize(doctor.getPatients());
            }
            tx.commit();
            return doctor;
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
    public void editDoctorInfo(Doctor doctor){
        Session session = sessionFactory.getCurrentSession();
        Doctor currentDoctorInfo = (Doctor)session.get(Doctor.class, doctor.getId());
        currentDoctorInfo.setFullName(doctor.getFullName());
        currentDoctorInfo.setLogin(doctor.getLogin());
        currentDoctorInfo.setPassword(doctor.getPassword());
        currentDoctorInfo.setPhoneNumber(doctor.getPhoneNumber());
        currentDoctorInfo.setEmail(doctor.getEmail());
        currentDoctorInfo.setJobPost(doctor.getJobPost());
        currentDoctorInfo.setJobPlace(doctor.getJobPlace());
        session.save(currentDoctorInfo);
    }

    @Override
    public void removeDoctor(Integer id){
        /*
        Doctor doctor = (Doctor)sessionFactory.getCurrentSession().load(Doctor.class, id);
        if(doctor != null){
            sessionFactory.getCurrentSession().delete(doctor);
        }
        */
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Doctor doctor = (Doctor)session.get(Doctor.class, id);
            if(doctor != null) {
                session.delete(doctor);
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
}
