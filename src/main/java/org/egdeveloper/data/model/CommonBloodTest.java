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
@MedTest(CommonBloodTest.class)
@EntityID("commonBlood")
@DisplayName("Общий анализ крови")
@JsonSerialize(as = CommonBloodTest.class)
@JsonDeserialize(as = CommonBloodTest.class)
@EqualsAndHashCode(callSuper = true)
@ToString
public class CommonBloodTest extends MedicalTest {


    public CommonBloodTest(){
        super();
    }

    @Indicator
    @DisplayName("Гемоглобин")
    @IndicatorNorm(minCritical = 110.0, min = 120.0, max = 140.0, maxCritical = 150.0)
    @StatVariable
    @Getter @Setter
    private Double hemoglobin;

    @Indicator
    @Column(name = "RBCells")
    @DisplayName("Эритроциты")
    @IndicatorNorm(minCritical = 3.2, min = 3.7, max = 4.7, maxCritical = 5.2)
    @StatVariable
    @Getter @Setter
    private Double rbCells;

    @Indicator
    @Column(name = "colorIndex")
    @DisplayName("Цветовой показатель")
    @IndicatorNorm(minCritical = 0.75, min = 0.85, max = 0.95, maxCritical = 1.25)
    @StatVariable
    @Getter @Setter
    private Double colorIndex;

    @Indicator
    @Column(name = "IMRBCells")
    @DisplayName("Ретикулоциты")
    @IndicatorNorm(minCritical = 0.1, min = 0.2, max = 1.2, maxCritical = 1.3)
    @StatVariable
    @Getter @Setter
    private Double imrbCells;

    @Indicator
    @Column(name = "platelets")
    @DisplayName("Тромбоциты")
    @IndicatorNorm(minCritical = 160.0, min = 180.0, max = 300.0, maxCritical = 340.0)
    @StatVariable
    @Getter @Setter
    private Double platelets;

    @Indicator
    @Column(name = "ESR")
    @DisplayName("СОЭ")
    @IndicatorNorm(minCritical = 1.0, min = 3.0, max = 12.0, maxCritical = 18.0)
    @StatVariable
    @Getter @Setter
    private Double esr;

    @Indicator
    @Column(name = "WBCells")
    @DisplayName("Лейкоциты")
    @IndicatorNorm(minCritical = 3.0, min = 5.0, max = 7.0, maxCritical = 11.0)
    @StatVariable
    @Getter @Setter
    private Double wbCells;

    @Indicator
    @Column(name = "bandCells")
    @DisplayName("Палочкоядерные")
    @IndicatorNorm(minCritical = 0.5, min = 1.5, max = 5.0, maxCritical = 7.0)
    @StatVariable
    @Getter @Setter
    private Double bandCells;

    @Indicator
    @Column(name = "segmentCells")
    @DisplayName("Сегментоядерные")
    @IndicatorNorm(minCritical = 37.0, min = 57.0, max = 62.0, maxCritical = 82.0)
    @StatVariable
    @Getter @Setter
    private Double segmentCells;

    @Indicator
    @Column(name = "eosinCells")
    @DisplayName("Эозинофилы")
    @IndicatorNorm(minCritical = 0.0, min = 0.0, max = 4.0, maxCritical = 6.0)
    @StatVariable
    @Getter @Setter
    private Double eosinCells;

    @Indicator
    @Column(name = "basophil")
    @DisplayName("Базофилы")
    @IndicatorNorm(minCritical = 0.0, min = 0.0, max = 0.9, maxCritical = 1.1)
    @StatVariable
    @Getter @Setter
    private Double basophil;

    @Indicator
    @Column(name = "NKCells")
    @DisplayName("Лимфоциты")
    @IndicatorNorm(minCritical = 13.0, min = 23.0, max = 35.0, maxCritical = 45.0)
    @StatVariable
    @Getter @Setter
    private Double nkCells;

    @Indicator
    @Column(name = "monoCells")
    @DisplayName("Моноциты")
    @IndicatorNorm(minCritical = 1.0, min = 3.0, max = 7.0, maxCritical = 11.0)
    @StatVariable
    @Getter @Setter
    private Double monoCells;

}
