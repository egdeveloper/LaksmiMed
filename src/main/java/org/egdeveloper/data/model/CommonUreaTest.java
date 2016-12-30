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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
@MedTest(CommonUreaTest.class)
@EntityID("commonUrea")
@DisplayName("Общий анализ мочи")
@JsonSerialize(as = CommonUreaTest.class)
@JsonDeserialize(as = CommonUreaTest.class)
@EqualsAndHashCode(callSuper = true)
@ToString
public class CommonUreaTest extends MedicalTest {

    public CommonUreaTest(){
        super();
    }

    @Indicator
    @Column(name = "amount")
    @DisplayName("Количество мочи")
    @IndicatorNorm(minCritical = 1008.0, min = 1018.0, max = 1025.0, maxCritical = 1035.0)
    @StatVariable
    @Getter @Setter
    private Double amount;

    @Indicator
    @Column(name = "PH")
    @DisplayName("Ph")
    @IndicatorNorm(minCritical = 3.5, min = 5.5, max = 7.0, maxCritical = 9.0)
    @StatVariable
    @Getter @Setter
    private Double ph;

    @Indicator
    @Column(name = "WBCells")
    @DisplayName("Лейкоциты")
    @IndicatorNorm(minCritical = 0.0, min = 0.0, max = 5.0, maxCritical = 7.0)
    @StatVariable
    @Getter @Setter
    private Double wbCells;

    @Indicator
    @Column(name = "RBCells")
    @DisplayName("Эритроциты")
    @IndicatorNorm(minCritical = 0.0, min = 0.0, max = 9.0, maxCritical = 11.0)
    @StatVariable
    @Getter @Setter
    private Double rbCells;

    @Indicator
    @Column(name = "color", nullable = false)
    @DisplayName("Цвет")
    @Getter @Setter
    private String color;

    @Indicator
    @Column(name = "transparency")
    @NotNull
    @DisplayName("Прозрачность")
    @Getter @Setter
    private String transparency;

    @Indicator
    @Column(name = "protein", nullable = false)
    @DisplayName("Белок")
    @IndicatorNorm(minCritical = 0.0, min = 0.0, max = 0.033, maxCritical = 0.033)
    @StatVariable
    @Getter @Setter
    private Double protein;

    @Indicator
    @Column(name = "glucose", nullable = false)
    @DisplayName("Глюкоза")
    @Getter @Setter
    private String glucose;

    @Indicator
    @Column(name = "ketoneBodies", nullable = false)
    @DisplayName("Кетоновые тела")
    @Getter @Setter
    private String ketoneBodies;

    @Indicator
    @Column(name = "bloodReaction", nullable = false)
    @DisplayName("Реакция на кровь")
    @Getter @Setter
    private String bloodReaction;

    @Indicator
    @Column(name = "biliRuby", nullable = false)
    @DisplayName("Билирубин")
    @Getter @Setter
    private String biliRuby;

    @Indicator
    @Column(name = "mucus", nullable = false)
    @DisplayName("Слизь")
    @Getter @Setter
    private String mucus;

    @Indicator
    @Column(name = "bacteria", nullable = false)
    @DisplayName("Бактерии")
    @Getter @Setter
    private String bacteria;

    @Indicator
    @Column(name = "salt", nullable = false)
    @DisplayName("Соли")
    @Getter @Setter
    private String salt;

    @Indicator
    @Column(name = "ureaBilins", nullable = false)
    @DisplayName("Уробилиноиды")
    @Getter @Setter
    private String ureaBilins;

    @Indicator
    @Column(name = "cylinder", nullable = false)
    @DisplayName("Цилиндры")
    @Getter @Setter
    private String cylinder;
}