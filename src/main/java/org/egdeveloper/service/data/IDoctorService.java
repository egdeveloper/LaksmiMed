package org.egdeveloper.service.data;


import org.egdeveloper.data.model.Doctor;

import java.util.List;

public interface IDoctorService {
    void saveDoctor(Doctor doctor);
    void updateDoctor(Doctor doctor);
    List<Doctor> findDoctors();
    Doctor findDoctorByID(Integer doctorId);
    void deleteDoctor(Integer id);
    Doctor authDoctor(String login);
}
