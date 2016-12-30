package org.egdeveloper.service.data;


import org.egdeveloper.data.model.Doctor;

import java.util.List;

public interface IDoctorService {
    void addDoctor(Doctor doctor);
    void updateDoctorInfo(Doctor doctor);
    List<Doctor> getDoctors();
    Doctor getDoctorByID(Integer doctorId);
    void removeDoctor(Integer id);
    Doctor getDoctorByLogin(String login);
}
