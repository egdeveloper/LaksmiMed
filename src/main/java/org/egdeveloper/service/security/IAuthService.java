package org.egdeveloper.service.security;

import org.egdeveloper.data.model.Doctor;
import org.egdeveloper.web.form.Login;
import org.egdeveloper.web.form.Signup;

public interface IAuthService {
    void register(Signup newUserAccount);
    Doctor auth(Login login);
}
