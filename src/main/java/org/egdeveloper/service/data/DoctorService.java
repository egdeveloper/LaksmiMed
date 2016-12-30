package org.egdeveloper.service.data;

import org.egdeveloper.data.dao.IDoctorDAO;
import org.egdeveloper.data.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DoctorService implements IDoctorService{

    @Autowired
    @Qualifier("doctorDAO")
    private IDoctorDAO doctorDAO;

    @Override
    public void addDoctor(Doctor doctor){
        doctorDAO.addDoctor(doctor);
    }

    @Override
    public void updateDoctorInfo(Doctor doctor) {
        doctorDAO.updateDoctorInfo(doctor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Doctor> getDoctors(){
        return doctorDAO.getDoctors();
    }

    @Override
    public Doctor getDoctorByID(Integer doctorID) {
        return doctorDAO.getDoctorById(doctorID);
    }

    @Override
    public void removeDoctor(Integer id){
        doctorDAO.removeDoctor(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Doctor getDoctorByLogin(String login){
        return doctorDAO.getDoctorByLogin(login);
    }
}
