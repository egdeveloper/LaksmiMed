package org.egdeveloper.web.controllers;

import org.egdeveloper.data.model.*;
import org.egdeveloper.service.data.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes(value = {"doctorAccount", "patient"})
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @RequestMapping(value = "/createPatientEntry")
    public String patientRegistrationPage(@ModelAttribute("doctorAccount") Doctor doctor, ModelMap modelMap){
        modelMap.addAttribute("patientEntry", new Patient());
        return "PatientPages/PatientEditPage";
    }

    @RequestMapping(value = "/createPatientEntry", method = RequestMethod.POST)
    public String createPatientEntry(@ModelAttribute("patientEntry") @Valid Patient patient,
                                     BindingResult bindingResult, ModelMap modelMap, HttpSession session, SessionStatus sessionStatus)
    {
        Doctor doctor = (Doctor)session.getAttribute("doctorAccount");
        if(bindingResult.hasErrors()) {
            return "PatientPages/PatientEditPage";
        }
        patientService.savePatient(doctor, patient);
        sessionStatus.setComplete();
        return "redirect:/patientList";
    }

    @RequestMapping(value = "/updatePatientEntry", method = RequestMethod.POST)
    public String updatePatientEntry(@ModelAttribute @Valid Patient patient,
                                     BindingResult bindingResult, ModelMap modelMap, HttpSession session)
    {
        if(!bindingResult.hasErrors())
            patientService.updatePatient(patient);
        return "redirect:/personalPatientPage";
    }

    @RequestMapping(value = "/patient/personal-page")
    public String personalPatientPage(@ModelAttribute Patient patient, ModelMap modelMap){
        return "PatientPages/PersonalPatientPage";
    }

    //get methods


    @RequestMapping("/test/{test_name}")
    public String saveMedicalTest(@PathVariable String test_name, ModelMap modelMap){
        switch (test_name) {
            case "bio_chem":
                modelMap.put("medicalTest", new BioChemTest());
                return "PatientPages/BiochemTestPage";
            case "common_blood":
                modelMap.put("medicalTest", new CommonBloodTest());
                return "PatientPages/CommonBloodTestPage";
            case "common_urea":
                modelMap.put("medicalTest", new CommonUreaTest());
                return "PatientPages/CommonUreaTestPage";
            case "daily_excreation":
                modelMap.put("medicalTest", new DailyExcreationTest());
                return "PatientPages/DailyExcreationTestPage";
            case "titration_test":
                modelMap.put("medicalTest", new TitrationTest());
                return "PatientPages/TitrationTestPage";
            case "urea_color":
                modelMap.put("medicalTest", new UreaColorTest());
                return "PatientPages/UreaColorTestPage";
            case "stone_in_vitro":
                modelMap.put("medicalTest", new StoneInVitroTest());
                return "PatientPages/StoneInVitroTestPage";
            case "stone_in_vivo":
                modelMap.put("medicalTest", new StoneInVivoTest());
                return "PatientPages/StoneInVivoTestPage";
        }
        return "redirect:personalPatientPage";
    }

//    @RequestMapping(value = "/test/{test_name}", method = RequestMethod.POST)
//    public String saveMedicalTest(@PathVariable String test_name, @ModelAttribute("medicalTest") @Valid MedicalTest test,
//                                  BindingResult bindingResult,
//                                  HttpSession session)
//    {
//        return __saveMedicalTest(test, bindingResult, session, "redirect:test/" + test_name);
//    }

    @RequestMapping(value = "/test/bio_chem", method = RequestMethod.POST)
    public String addBiochemTest(@ModelAttribute("medicalTest") @Valid BioChemTest test,
                                 BindingResult bindingResult,
                                 ModelMap modelMap, HttpSession session)
    {
        if(!bindingResult.hasErrors()){
            Patient patient = (Patient)session.getAttribute("patient");
            if(patient != null){
                patientService.saveTest(patient, test);
                return "redirect:/patient/personal-page";
            }
        }
        return "redirect:/test/bio_chem";
    }

    @RequestMapping(value = "/test/common_blood", method = RequestMethod.POST)
    public String addCommonBloodTest(@ModelAttribute("medicalTest") @Valid CommonBloodTest test,
                                     BindingResult bindingResult,
                                     ModelMap modelMap, HttpSession session)
    {
        if(!bindingResult.hasErrors()){
            Patient patient = (Patient)session.getAttribute("patient");
            if(patient != null){
                patientService.saveTest(patient, test);
                return "redirect:/patient/personal-page";
            }
        }
        return "redirect:/test/common_blood";
    }

    @RequestMapping(value = "/test/common_urea", method = RequestMethod.POST)
    public String addCommonUreaTest(@ModelAttribute("commonUreaTest") @Valid CommonUreaTest test,
                                    BindingResult bindingResult,
                                    ModelMap modelMap, HttpSession session)
    {
        if(!bindingResult.hasErrors()){
            Patient patient = (Patient)session.getAttribute("patient");
            if(patient != null){
                patientService.saveTest(patient, test);
                return "redirect:/patient/personal-page";
            }
        }
        return "redirect:/test/common_urea";
    }

    @RequestMapping(value = "/test/daily_excreation", method = RequestMethod.POST)
    public String addDailyExcreationTest(@ModelAttribute("medicalTest") @Valid DailyExcreationTest test,
                                         BindingResult bindingResult,
                                         ModelMap modelMap, HttpSession session)
    {
        if(!bindingResult.hasErrors()){
            Patient patient = (Patient)session.getAttribute("patient");
            if(patient != null){
                patientService.saveTest(patient, test);
                return "redirect:/patient/personal-page";
            }
        }
        return "redirect:/test/daily_excreation";
    }

    @RequestMapping(value = "/test/titration", method = RequestMethod.POST)
    public String addTitrationTest(@ModelAttribute("medicalTest") @Valid TitrationTest test,
                                   BindingResult bindingResult,
                                   ModelMap modelMap, HttpSession session)
    {
        if(!bindingResult.hasErrors()){
            Patient patient = (Patient)session.getAttribute("patient");
            if(patient != null){
                patientService.saveTest(patient, test);
                return "redirect:/patient/personal-page";
            }
        }
        return "redirect:/test/titration";
    }

    @RequestMapping(value = "/test/urea_color", method = RequestMethod.POST)
    public String addUreaColorTest(@ModelAttribute("medicalTest") @Valid UreaColorTest test,
                                   BindingResult bindingResult,
                                   ModelMap modelMap, HttpSession session)
    {
        if(!bindingResult.hasErrors()){
            Patient patient = (Patient)session.getAttribute("patient");
            if(patient != null){
                patientService.saveTest(patient, test);
                return "redirect:/patient/personal-page";
            }
        }
        return "redirect:/test/urea_color";
    }

    @RequestMapping(value = "/test/stone_in_vivo", method = RequestMethod.POST)
    public String addStoneInVivoTest(@ModelAttribute("medicalTest") @Valid StoneInVivoTest test,
                                     BindingResult bindingResult,
                                     ModelMap modelMap, HttpSession session)
    {
        if(!bindingResult.hasErrors()){
            Patient patient = (Patient)session.getAttribute("patient");
            if(patient != null){
                patientService.saveTest(patient, test);
                return "redirect:/patient/personal-page";
            }
        }
        return "redirect:/test/stone_in_vivo";
    }

    @RequestMapping(value = "/test/stone_in_vitro", method = RequestMethod.POST)
    public String addStoneInVitroTest(@ModelAttribute("medicalTest") @Valid StoneInVitroTest test,
                                      BindingResult bindingResult,
                                      ModelMap modelMap, HttpSession session)
    {
        if(!bindingResult.hasErrors()){
            Patient patient = (Patient)session.getAttribute("patient");
            if(patient != null){
                patientService.saveTest(patient, test);
                return "redirect:/patient/personal-page";
            }
        }
        return "redirect:/test/stone_in_vitro";
    }

    @RequestMapping(value = "/viewMedicalTest/{testName}/{testID}")
    public String showMedicalTestInfo(@PathVariable("testName") String testName,
                                      @PathVariable("testID") Integer testID,
                                      ModelMap modelMap,
                                      HttpSession session)
    {
        Patient patient = (Patient) session.getAttribute("patient");
        MedicalTest medicalTest = null;
        if(testName.equals("bioChem"))
            medicalTest = patient.getBioChemTests().stream().filter(t -> testID == t.getId()).findFirst().get();
        if(testName.equals("commonBlood"))
            medicalTest = patient.getCommonBloodTests().stream().filter(t -> testID == t.getId()).findFirst().get();
        if(testName.equals("commonUrea"))
            medicalTest = patient.getCommonUreaTests().stream().filter(t -> testID == t.getId()).findFirst().get();
        if(testName.equals("dailyExcreation"))
            medicalTest = patient.getDailyExcreationTests().stream().filter(t -> testID == t.getId()).findFirst().get();
        if(testName.equals("titration"))
            medicalTest = patient.getTitrationTests().stream().filter(t -> testID == t.getId()).findFirst().get();
        if(testName.equals("ureaColorT"))
            medicalTest = patient.getUreaColorTests().stream().filter(t -> testID == t.getId()).findFirst().get();
        if(testName.equals("stoneInVivo"))
            medicalTest = patient.getStoneInVivoTests().stream().filter(t -> testID == t.getId()).findFirst().get();
        if(testName.equals("stoneInVitro"))
            medicalTest = patient.getStoneInVitroTests().stream().filter(t -> testID == t.getId()).findFirst().get();
        if(medicalTest != null) {
            modelMap.put("medicalTest", medicalTest);
            return "TestPages/View" + testName.substring(0, 1).toUpperCase() + testName.substring(1) + "Page";
        }
        return "redirect:/patient/personal-page";
    }

    @RequestMapping(value = "/patients/db", method = RequestMethod.POST)
    public String uploadPatientDB(@RequestParam MultipartFile patientDB){
        return "redirect:/doctor/patients";
    }

    private String __saveMedicalTest(MedicalTest test, BindingResult bindingResult, HttpSession session, String redirectURL){
        if(!bindingResult.hasErrors()){
            Patient patient = (Patient)session.getAttribute("patient");
            if(patient != null){
                patientService.saveTest(patient, test);
                return "redirect:/personalPatientPage";
            }
        }
        return redirectURL;
    }
}
