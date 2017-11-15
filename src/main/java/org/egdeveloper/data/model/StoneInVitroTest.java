package org.egdeveloper.data.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.egdeveloper.attributes.*;
import org.egdeveloper.data.model.customTypes.StoneXRay;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@MedTest(StoneInVitroTest.class)
@EntityID("stoneInVitro")
@DisplayName("Камень IN VITRO")
@JsonSerialize(as = StoneInVitroTest.class)
@JsonDeserialize(as = StoneInVitroTest.class)
@EqualsAndHashCode(callSuper = true)
@ToString
public class StoneInVitroTest extends MedicalTest {

    public StoneInVitroTest(){
        super();
    }

    @Indicator
    @Column(name = "stoneType", nullable = false)
    @DisplayName("Вид камня")
    @Getter @Setter
    private String stoneType;

    @Indicator
    @Column(name = "xray", nullable = false)
    @Enumerated(EnumType.STRING)
    @DisplayName("Рентгеноскопия")
    @Getter @Setter
    private StoneXRay xray;

    @Indicator
    @Column(name = "hardness")
    @DisplayName("Твердость")
    @Getter @Setter
    private Double hardness;

    @Indicator
    @Column(name = "addInfo", nullable = false)
    @DisplayName("Дополнительная информация о камне")
    @Getter @Setter
    private String addInfo;
}
