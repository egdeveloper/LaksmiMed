package org.egdeveloper.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.egdeveloper.converters.deserializers.DoctorDeserializer;
import org.egdeveloper.converters.serializers.DoctorSerializer;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
//@JsonSerialize(as = Doctor.class)
//@JsonDeserialize(as = Doctor.class)
@JsonSerialize(using = DoctorSerializer.class)
@JsonDeserialize(using = DoctorDeserializer.class)
@EqualsAndHashCode(callSuper = true)
@ToString
//@JsonTypeName("doctor")
public class Doctor extends Person implements Serializable{

    public Doctor(){
        this.fullName = "";
        this.phoneNumber = "";
        this.email = "";
        this.jobPlace = "";
        this.jobPost = "";
    }

    @NotEmpty(message = "Введите логин!")
    @Column(name = "login", nullable = false)
    @Getter @Setter
    private String login;

    @NotEmpty(message = "Введите пароль!")
    @Size(min = 6, max = 255, message = "Пароль должен состоять не менее из 6 символов")
    @Column(name = "password", nullable = false)
    @Getter @Setter
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "doctor")
    @JsonIgnore
    @Getter @Setter
    private List<Patient> patients = new ArrayList<>();
}
