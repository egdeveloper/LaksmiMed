package org.egdeveloper.web.controllers;

import org.egdeveloper.data.model.Doctor;
import org.egdeveloper.service.security.IAuthService;
import org.egdeveloper.web.form.Login;
import org.egdeveloper.web.form.Signup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class HomeController {

    @Autowired
    private IAuthService userAuthService;

    @Autowired
    @Qualifier("signupValidator")
    private Validator validator;

    @RequestMapping(value = {"/", "/index"})
    public String index(ModelMap modelMap){
        modelMap.addAttribute("loginAuth", new Login());
        modelMap.addAttribute("signupData", new Signup());
        return "HomePages/IndexPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String logIn(@ModelAttribute @Valid Login loginAuth, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes attributes){
        if(!bindingResult.hasErrors()) {
            Doctor doctor = userAuthService.auth(loginAuth);
            if(doctor != null) {
                attributes.addFlashAttribute("doctor", doctor);
                return "redirect:/main";
            }
        }
        modelMap.addAttribute("loginError", "Вы не правильно ввели логин или пароль!");
        return "redirect:/";
    }

    @RequestMapping(value = "/about2")
    public String about(){
        return "HomePages/About2Page";
    }

    @RequestMapping(value = "/help")
    public String help(){
        return "HomePages/HelpPage";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerNewDoctor(@ModelAttribute("signupData") @Valid Signup signup, BindingResult bindingResult, ModelMap modelMap){
        validator.validate(signup, bindingResult);
        if(bindingResult.hasErrors()){
            return "redirect:/";
        }
        else {
            userAuthService.register(signup);
            return "redirect:/";
        }
    }
}
