package org.egdeveloper.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
import java.io.Serializable;

//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = Doctor.class, name = "doctor"),
//        @JsonSubTypes.Type(value = Patient.class, name = "patient")
//})
@MappedSuperclass
abstract class Person extends AbstractModel implements Serializable{

    @Size(max = 255)
//    @NotEmpty(message = "Введите ФИО!")
    @Column(name = "fullName", nullable = false)
    @Getter @Setter
    protected String fullName;

//    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
//            message = "Введите email в правильном формате!"
//    )
    @Column(name = "email", nullable = false)
    @Getter @Setter
    protected String email;

    @Column(name = "jobPlace", nullable = false)
    @Getter @Setter
    protected String jobPlace;

    @Column(name = "jobPost", nullable = false)
    @Getter @Setter
    protected String jobPost;

    @Column(name = "phoneNumber", nullable = false)
    @Getter @Setter
    protected String phoneNumber;
}
