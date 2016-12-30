package org.egdeveloper.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.egdeveloper.attributes.DisplayName;
import org.egdeveloper.attributes.EntityID;
import org.egdeveloper.attributes.MedTest;
import org.egdeveloper.converters.deserializers.CustomDateTimeDeserializer;
import org.egdeveloper.converters.serializers.CustomDateTimeSerializer;
import org.egdeveloper.data.model.customTypes.Attachment;
import org.egdeveloper.data.model.customTypes.PatientState;
import org.egdeveloper.data.model.customTypes.TreatmentNumber;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BioChemTest.class, name = "bioChem"),
        @JsonSubTypes.Type(value = CommonBloodTest.class, name = "commonBlood"),
        @JsonSubTypes.Type(value = CommonUreaTest.class, name = "commonUrea"),
        @JsonSubTypes.Type(value = DailyExcreationTest.class, name = "dailyExcreation"),
        @JsonSubTypes.Type(value = StoneInVitroTest.class, name = "stoneInVitro"),
        @JsonSubTypes.Type(value = StoneInVivoTest.class, name = "stoneInVivo"),
        @JsonSubTypes.Type(value = TitrationTest.class, name = "titration"),
        @JsonSubTypes.Type(value = UreaColorTest.class, name = "ureaColor")
})
@MedTest(MedTest.class)
@MappedSuperclass
public abstract class MedicalTest extends AbstractModel implements Serializable, Comparable<MedicalTest>{

    public MedicalTest(){
        attachment = new HashSet<>();
    }

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Getter @Setter
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "testDate", nullable = false)
    @DisplayName("Дата проведения анализа")
    private LocalDate testDate;

    @Getter @Setter
    @Column(name = "treatment", nullable = false)
    @DisplayName("Лечение")
    private String treatment;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "treatmentNumber", nullable = false)
    @DisplayName("Стадия лечения")
    private TreatmentNumber treatmentNumber;

    @Getter @Setter
    @Column(name = "description", nullable = false)
    @DisplayName("Дополнительная информация")
    private String description;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "patientState")
    @DisplayName("Состояние пациента")
    private PatientState patientState;

    @Getter @Setter
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "TestAttachments", joinColumns = @JoinColumn(name = "testID"))
    @Fetch(FetchMode.SELECT)
    private Set<Attachment> attachment = new HashSet<>();

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private Patient patient;

    @Transient
    public String retrieveName(){
        return this.getClass().getAnnotation(DisplayName.class).value();
    }

    @Transient
    public String retrieveEntityID(){
        return this.getClass().getAnnotation(EntityID.class).value();
    }

    @Override
    @Transient
    public int compareTo(MedicalTest otherTest){
        if(this.getTestDate().isEqual(otherTest.getTestDate()))
            return 0;
        else
            return this.getTestDate().isBefore(otherTest.getTestDate()) ? -1 : 1;
    }
}
