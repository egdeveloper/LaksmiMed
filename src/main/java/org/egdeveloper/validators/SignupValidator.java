package org.egdeveloper.validators;

import org.egdeveloper.web.form.Signup;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SignupValidator implements Validator {

    public boolean supports(Class clazz) {
        return SignupValidator.class.isAssignableFrom(clazz);
    }

    public void validate(Object o, Errors errors) {
        Signup signup = (Signup)o;
        if(!signup.getPassword().equals(signup.getConfirmPassword())){
            errors.rejectValue("confirmPassword", "validation.passwordIsNotConfirmed");
        }
    }
}
