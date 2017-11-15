package org.egdeveloper.data.dao.impl;

import org.egdeveloper.data.dao.IDoctorDAO;
import org.egdeveloper.data.model.Doctor;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class DoctorDAO implements IDoctorDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public DoctorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveDoctor(Doctor doctor){
        try (Session session = sessionFactory.openSession()) {
            session.save(doctor);
        }
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        try (Session session = sessionFactory.openSession()) {
            session.update(doctor);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Doctor> findDoctors(){
        try (Session session = sessionFactory.openSession()) {
            return  (List<Doctor>) session.createQuery("from Doctor").list();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Doctor authDoctor(String login){
        Doctor doctor = null;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Doctor where login = :login");
            query.setParameter("login", login);
            List doctorList = query.list();
            if (doctorList != null && doctorList.size() > 0) {
                doctor = (Doctor) doctorList.get(0);
                Hibernate.initialize(doctor.getPatients());
            }
            return doctor;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Doctor findDoctorById(Integer id) {
        Doctor doctor;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Doctor where id = :id");
            query.setParameter("id", id);
            doctor = (Doctor) query.uniqueResult();
            if (doctor != null) {
                Hibernate.initialize(doctor.getPatients());
            }
            return doctor;
        }
    }

    @Override
    public void editDoctorInfo(Doctor doctor){
        Session session = sessionFactory.getCurrentSession();
        Doctor currentDoctorInfo = session.get(Doctor.class, doctor.getId());
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
    public void deleteDoctor(Integer id){
        try (Session session = sessionFactory.openSession()) {
            Doctor doctor = session.get(Doctor.class, id);
            if (doctor != null) {
                session.delete(doctor);
            }
        }
    }
}
