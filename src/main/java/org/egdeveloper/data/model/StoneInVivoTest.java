package org.egdeveloper.data.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.egdeveloper.attributes.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@MedTest(StoneInVivoTest.class)
@EntityID("stoneInVivo")
@DisplayName("Камень IN VIVO")
@JsonSerialize(as = StoneInVivoTest.class)
@JsonDeserialize(as = StoneInVivoTest.class)
@EqualsAndHashCode(callSuper = true)
@ToString
public class StoneInVivoTest extends MedicalTest {

    public StoneInVivoTest(){
        super();
    }

    @Indicator
    @Column(name = "size", nullable = true)
    @DisplayName("Размер")
    @StatVariable
    @Getter @Setter
    private Double size;

    @Indicator
    @Column(name = "density", nullable = true)
    @DisplayName("Плотность")
    @StatVariable
    @Getter @Setter
    private Double density;

    @Indicator
    @Column(name = "location", nullable = false)
    @DisplayName("Локализация")
    @Getter @Setter
    private String location;

    @Indicator
    @Column(name = "addInfo", nullable = false)
    @DisplayName("Дополнительная информация о камне")
    @Getter @Setter
    private String addInfo;
}
