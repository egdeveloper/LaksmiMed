package org.egdeveloper.service.data.impl;

import org.egdeveloper.data.dao.IDoctorDAO;
import org.egdeveloper.data.model.Doctor;
import org.egdeveloper.service.data.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService implements IDoctorService {

    private final IDoctorDAO doctorDAO;

    @Autowired
    public DoctorService(IDoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    @Override
    public void saveDoctor(Doctor doctor){
        doctorDAO.saveDoctor(doctor);
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        doctorDAO.updateDoctor(doctor);
    }

    @Override
    public List<Doctor> findDoctors(){
        return doctorDAO.findDoctors();
    }

    @Override
    public Doctor findDoctorByID(Integer doctorID) {
        return doctorDAO.findDoctorById(doctorID);
    }

    @Override
    public void deleteDoctor(Integer id){
        doctorDAO.deleteDoctor(id);
    }

    @Override
    public Doctor authDoctor(String login){
        return doctorDAO.authDoctor(login);
    }
}
