package org.egdeveloper.web.controllers;

import org.egdeveloper.data.model.Doctor;
import org.egdeveloper.data.model.Patient;
import org.egdeveloper.service.data.IDoctorService;
import org.egdeveloper.service.data.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@SessionAttributes("doctor")
public class MainController {

    private final IPatientService patientService;

    private final IDoctorService doctorService;

    @Autowired
    public MainController(IPatientService patientService, IDoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @RequestMapping(value = "/main")
    public String mainDoctorPage(@ModelAttribute Doctor doctor, BindingResult bindingResult, ModelMap modelMap, HttpSession session){
//        return "DoctorPages/MainPage";
        return "redirect:/doctor/patients";
    }

    @RequestMapping(value = "/about")
    public String aboutPage(){
        return "HomePages/AboutPage";
    }

    @RequestMapping(value = "/doctor/patients")
    public String patientList(ModelMap modelMap, HttpSession session){
        Doctor doctorAccount = (Doctor) session.getAttribute("doctor");
        modelMap.addAttribute("patients", patientService.findPatients(doctorAccount.getId()));
        return "DoctorPages/PatientListPage";
    }

    @RequestMapping(value = "/doctor/personal-page")
    public String personalPage(){
        return "DoctorPages/PersonalPage";
    }

    @RequestMapping(value = "/doctor/update-info", method = RequestMethod.POST)
    public String updatePersonalDoctorInfo(@ModelAttribute Doctor doctor, BindingResult bindingResult){
        if(!bindingResult.hasErrors())
            doctorService.updateDoctorInfo(doctor);
        return "redirect:/personalPage";
    }

    @RequestMapping(value = "/patientInfoEditor")
    public String getPatientInfoEditor(RedirectAttributes attributes, HttpSession session){
        Doctor doctorAccount = (Doctor) session.getAttribute("doctor");
        attributes.addFlashAttribute("doctorAccount", doctorAccount);
        return "redirect:/createPatientEntry";
    }

    @RequestMapping(value = "/patients/{patientId}")
    public String getPatientPersonalPage(@PathVariable Integer patientId, RedirectAttributes redirectAttributes){
        Patient patient = patientService.findPatientById(patientId);
        if(patient != null) {
            redirectAttributes.addFlashAttribute("patient", patient);
            return "redirect:/patient/personal-page";
        }
        else
            return "redirect:/main";
    }

    @RequestMapping(value = "/deletePatientEntry/{patientID}", method = RequestMethod.POST)
    public String deletePatientEntry(@PathVariable Integer patientID,
                                     RedirectAttributes redirectAttributes,
                                     HttpSession session)
    {
        Doctor doctorAccount = (Doctor)session.getAttribute("doctor");
        patientService.removePatientAndUpdateDoctor(doctorAccount, patientID);
        return "redirect:/doctor/patients";
    }

    @RequestMapping(value = "/logout")
    public String logout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @RequestMapping(value = "/pages/patients/statistics")
    public String patientStatisticsPage(){
        return "StatisticsPages/StatBeforeTreatmentPage";
    }

    @RequestMapping(value = "/pages/tests/stone-components")
    public String stoneComponentsStatPage(){
        return "StatisticsPages/StoneComponentsStatPage";
    }

    @RequestMapping(value = "/pages/tests/disease-stone")
    public String stoneTypesAndDiseasesPage(){
        return "StatisticsPages/StatStoneTypesAndDiseasesPage";
    }

    @RequestMapping(value = "/pages/tests/indicator-stone")
    public String indDevsStoneStatPage(){
        return "StatisticsPages/IndDevsStoneStatPage";
    }
}
