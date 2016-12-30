package org.egdeveloper.service.web;

import org.egdeveloper.data.model.Doctor;
import org.egdeveloper.service.data.IDoctorService;
import org.egdeveloper.service.security.IAuthService;
import org.egdeveloper.web.form.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Developer: egdeveloper
 * Creation-Date: 12.08.16
 */

@RestController
@RequestMapping("/rest-service")
public class DoctorWebService implements IDoctorWebService{

    @Autowired
    private IDoctorService doctorService;

    @Autowired
    private IAuthService authService;

    @Override
    @RequestMapping(value = "/doctor", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        doctorService.addDoctor(doctor);
        return doctor;
    }

    @Override
    @RequestMapping(value = "/doctor", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public Doctor updateDoctor(@RequestBody Doctor doctor) {
        doctorService.updateDoctorInfo(doctor);
        return doctor;
    }

    @Override
    @RequestMapping(value = "/doctor/{doctorID}", produces = "application/json")
    public Doctor findDoctor(@PathVariable Integer doctorID) {
        return doctorService.getDoctorByID(doctorID);
    }

    @Override
    @RequestMapping(value = "/doctor/{doctorID}", method = RequestMethod.DELETE, produces = "application/json")
    public void removeDoctor(@PathVariable Integer doctorID) {
        doctorService.removeDoctor(doctorID);
    }

    @Override
    @RequestMapping(value = "/doctor", produces = "application/json")
    public Doctor authDoctor(@RequestParam String login, @RequestParam String password) {
        Login creds = new Login();
        creds.setLogin(login);
        creds.setPassword(password);
        return authService.auth(creds);
    }
}
