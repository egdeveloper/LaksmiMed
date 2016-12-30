package org.egdeveloper.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.egdeveloper.attributes.MedTest;
import org.egdeveloper.converters.deserializers.CustomDateTimeDeserializer;
import org.egdeveloper.converters.deserializers.PatientDeserializer;
import org.egdeveloper.converters.serializers.CustomDateTimeSerializer;
import org.egdeveloper.converters.serializers.PatientSerializer;
import org.egdeveloper.data.model.customTypes.*;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
//@JsonSerialize(as = Patient.class)
//@JsonDeserialize(as = Patient.class)
//@JsonTypeName("patient")
@JsonSerialize(using = PatientSerializer.class)
@JsonDeserialize(using = PatientDeserializer.class)
@EqualsAndHashCode(callSuper = true)
@ToString
public class Patient extends Person implements Serializable{

    public Patient(){}

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    @Getter @Setter
    private Gender gender;

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "birthdate", nullable = false)
    @Getter @Setter
    private LocalDate birthdate;

    @NotEmpty(message = "Введите номер карты")
    @Column(name = "cardNumber", nullable = false)
    @Getter @Setter
    private String cardNumber;

    @Column(name = "passport", nullable = false)
    @Getter @Setter
    private String passport;

    @Column(name = "country", nullable = false)
    @Getter @Setter
    private String country;

    @Column(name = "postIndex", nullable = false)
    @Getter @Setter
    private String postIndex;

    @Column(name = "region", nullable = false)
    @Getter @Setter
    private String region;

    @Column(name = "city", nullable = false)
    @Getter @Setter
    private String city;

    @Column(name = "address", nullable = false)
    @Getter @Setter
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "patientState", nullable = false)
    @Getter @Setter
    private PatientState patientState;

    @Enumerated(EnumType.STRING)
    @Column(name = "rh", nullable = false)
    @Getter @Setter
    private Rh rh;

    @Enumerated(EnumType.STRING)
    @Column(name = "bloodGroup", nullable = false)
    @Getter @Setter
    private BloodGroup bloodGroup;

    @Enumerated(EnumType.STRING)
    @Column(name = "disability", nullable = false)
    @Getter @Setter
    private Disability disability;

    @Column(name = "TIN", nullable = false)
    @Getter @Setter
    private String TIN;

    @Column(name = "OMICard", nullable = false)
    @Getter @Setter
    private String OMICard;

    @Column(name = "occupation", nullable = false)
    @Getter @Setter
    private String occupation;

    @Column(name = "jobConditions", nullable = false)
    @Getter @Setter
    private String jobConditions;

    @Column(name = "complaints", nullable = false)
    @Getter @Setter
    private String complaints;

    @Column(name = "premedication", nullable = false)
    @Getter @Setter
    private String premedication;

    @Column(name = "associatedDisease", nullable = false)
    @Getter @Setter
    private String associatedDisease;

    @Column(name = "preMedicalSupplies", nullable = false)
    @Getter @Setter
    private String preMedicalSupplies;

    @Column(name = "badHabits", nullable = false)
    @Getter @Setter
    private String badHabits;

    @Column(name = "preUreaStoneDescription", nullable = false)
    @Getter @Setter
    private String preUreaStoneDescription;

    @Column(name = "diseaseDuration", nullable = false)
    @Getter @Setter
    private String diseaseDuration;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    @Getter @Setter
    @JsonIgnore
    private Doctor doctor;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "patient")
    @MedTest(BioChemTest.class)
    @Getter @Setter
    @JsonIgnore
    private Set<BioChemTest> bioChemTests = new TreeSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "patient")
    @MedTest(CommonBloodTest.class)
    @Getter @Setter
    @JsonIgnore
    private Set<CommonBloodTest> commonBloodTests = new TreeSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "patient")
    @MedTest(CommonUreaTest.class)
    @Getter @Setter
    @JsonIgnore
    private Set<CommonUreaTest> commonUreaTests = new TreeSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "patient")
    @MedTest(DailyExcreationTest.class)
    @Getter @Setter
    @JsonIgnore
    private Set<DailyExcreationTest> dailyExcreationTests = new TreeSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "patient")
    @MedTest(TitrationTest.class)
    @Getter @Setter
    @JsonIgnore
    private Set<TitrationTest> titrationTests = new TreeSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "patient")
    @MedTest(UreaColorTest.class)
    @Getter @Setter
    @JsonIgnore
    private Set<UreaColorTest> ureaColorTests = new TreeSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "patient")
    @MedTest(StoneInVivoTest.class)
    @Getter @Setter
    @JsonIgnore
    private Set<StoneInVivoTest> stoneInVivoTests = new TreeSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "patient")
    @MedTest(StoneInVitroTest.class)
    @Getter @Setter
    @JsonIgnore
    private Set<StoneInVitroTest> stoneInVitroTests = new TreeSet<>();



    @Transient
    public Set<MedicalTest> findTests(){
        Set<MedicalTest> tests = new HashSet<>();
        tests.addAll(getBioChemTests());
        tests.addAll(getCommonBloodTests());
        tests.addAll(getCommonUreaTests());
        tests.addAll(getDailyExcreationTests());
        tests.addAll(getTitrationTests());
        tests.addAll(getUreaColorTests());
        tests.addAll(getStoneInVivoTests());
        tests.addAll(getStoneInVitroTests());
        return tests;
    }

    @Transient
    public void addTest(MedicalTest test){
        test.setPatient(this);
        if(test instanceof BioChemTest){
            getBioChemTests().add((BioChemTest) test);
        }
        else if(test instanceof CommonBloodTest){
            getCommonBloodTests().add((CommonBloodTest) test);
        }
        else if(test instanceof CommonUreaTest){
            getCommonUreaTests().add((CommonUreaTest) test);
        }
        else if(test instanceof DailyExcreationTest){
            getDailyExcreationTests().add((DailyExcreationTest) test);
        }
        else if(test instanceof TitrationTest){
            getTitrationTests().add((TitrationTest) test);
        }
        else if(test instanceof UreaColorTest){
            getUreaColorTests().add((UreaColorTest) test);
        }
        else if(test instanceof StoneInVivoTest){
            getStoneInVivoTests().add((StoneInVivoTest) test);
        }
        else if(test instanceof StoneInVitroTest){
            getStoneInVitroTests().add((StoneInVitroTest) test);
        }
    }
}
