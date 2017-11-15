package org.egdeveloper.service.web;

import org.egdeveloper.data.model.Doctor;

/**
 * Developer: egdeveloper
 * Creation-Date: 11.08.16
 */
public interface IDoctorWebService {
    Doctor addDoctor(Doctor doctor);
    Doctor updateDoctor(Doctor doctor);
    Doctor findDoctor(Integer doctorId);
    void removeDoctor(Integer id);
    Doctor authDoctor(String login, String password);
}
