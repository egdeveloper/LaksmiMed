package org.egdeveloper.web.form;

import lombok.Getter;
import lombok.Setter;
import org.egdeveloper.data.model.Doctor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter @Setter
public class Signup {

//    @NotEmpty(message = "Введите свое ФИО!")
//    private String fullName;

    @NotEmpty(message = "Введите логин!")
    private String login;

    @NotEmpty(message = "Введите пароль!")
    @Size(min = 6, max = 255, message = "Пароль должен состоять не менее из 6 символов")
    private String password;

    @NotEmpty(message = "Вы не подтвердили пароль!")
    private String confirmPassword;

//    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
//            message = "Введите email в правильном формате!"
//    )
//    private String email;
//
//    private String jobPlace;
//
//    private String jobPost;

    private String phoneNumber;

    public Signup(){}

//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getConfirmPassword() {
//        return confirmPassword;
//    }
//
//    public void setConfirmPassword(String confirmPassword) {
//        this.confirmPassword = confirmPassword;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getJobPlace() {
//        return jobPlace;
//    }
//
//    public void setJobPlace(String jobPlace) {
//        this.jobPlace = jobPlace;
//    }
//
//    public String getJobPost() {
//        return jobPost;
//    }
//
//    public void setJobPost(String jobPost) {
//        this.jobPost = jobPost;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }

    public Doctor getDoctorAccount(){
        Doctor doctor = new Doctor();
//        doctor.setFullName(fullName);
        doctor.setLogin(login);
        doctor.setPassword(password);
//        doctor.setEmail(email);
//        doctor.setJobPlace(jobPlace);
//        doctor.setJobPost(jobPost);
//        doctor.setPhoneNumber(phoneNumber);
        return doctor;
    }

}
