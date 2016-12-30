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
@MedTest(BioChemTest.class)
@EntityID("bioChem")
@DisplayName("Биохимический анализ крови")
@EqualsAndHashCode(callSuper = true)
@ToString
@JsonSerialize(as = BioChemTest.class)
@JsonDeserialize(as = BioChemTest.class)
public class BioChemTest extends MedicalTest {


    public BioChemTest(){
        super();
    }

    @Indicator
    @DisplayName("Общий белок")
    @IndicatorNorm(minCritical = 56.0, min = 76.0, max = 77.0, maxCritical = 97.0)
    @StatVariable
    @Getter @Setter
    private Double commonProtein;

    @Indicator
    @DisplayName("Мочевина")
    @IndicatorNorm(minCritical = 1.2, min = 2.2, max = 7.8, maxCritical = 8.8)
    @StatVariable
    @Getter @Setter
    private Double urea;

    @Indicator
    @DisplayName("Креатинин")
    @IndicatorNorm(minCritical = 43.0, min = 63.0, max = 105.0, maxCritical = 125.0)
    @StatVariable
    @Getter @Setter
    private Double creatinine;

    @Indicator
    @DisplayName("Общий билирубин")
    @IndicatorNorm(minCritical = 1.5, min = 2.5, max = 18.0, maxCritical = 24.0)
    @StatVariable
    @Getter @Setter
    private Double commonBiliRuby;

    @Indicator
    @DisplayName("Связанный билирубин")
    @IndicatorNorm(minCritical = 0.0, min = 0.0, max = 2.9, maxCritical = 3.9)
    @StatVariable
    @Getter @Setter
    private Double linkedBiliRuby;

    @Indicator
    @DisplayName("Холестерин")
    @IndicatorNorm(minCritical = 3.13, min = 4.13, max = 4.7, maxCritical = 5.7)
    @StatVariable
    @Getter @Setter
    private Double cholesterol;

    @Indicator
    @Column(name = "TAG")
    @DisplayName("Триглицериды")
    @IndicatorNorm(minCritical = 0.0, min = 0.0, max = 1.6, maxCritical = 1.8)
    @StatVariable
    @Getter @Setter
    private Double tag;

    @Indicator
    @Column(name = "HDL")
    @DisplayName("Липопр. выс. кон.")
    @IndicatorNorm(minCritical = 0.8, min = 1.0, max = 1.44, maxCritical = 1.64)
    @StatVariable
    @Getter @Setter
    private Double hdl;

    @Indicator
    @Column(name = "LDL")
    @DisplayName("Липопр. низ. кон.")
    @IndicatorNorm(minCritical = 1.0, min = 1.4, max = 4.2, maxCritical = 4.6)
    @StatVariable
    @Getter @Setter
    private Double ldl;

    @Indicator
    @Column(name = "cholesterolRatio")
    @DisplayName("Коэф. атер.")
    @IndicatorNorm(minCritical = 0.0, min = 0.0, max = 2.8, maxCritical = 3.2)
    @StatVariable
    @Getter @Setter
    private Double cholesterolRatio;

    @Indicator
    @Column(name = "ALT")
    @DisplayName("Аланинаминотрасфераза")
    @IndicatorNorm(minCritical = 0.0, min = 0.0, max = 37.0, maxCritical = 47.0)
    @StatVariable
    @Getter @Setter
    private Double alt;

    @Indicator
    @Column(name = "AST")
    @DisplayName("Аспартатаминотрансфераза")
    @IndicatorNorm(minCritical = 0.0, min = 0.0, max = 32.0, maxCritical = 42.0)
    @StatVariable
    @Getter @Setter
    private Double ast;

    @Indicator
    @Column(name = "ALKP")
    @DisplayName("Щелочная фосфотаза")
    @IndicatorNorm(minCritical = 70.0, min = 90.0, max = 276.0, maxCritical = 336.0)
    @StatVariable
    @Getter @Setter
    private Double alkp;

    @Indicator
    @DisplayName("Альбумин")
    @IndicatorNorm(minCritical = 35.0, min = 35.0, max = 50.0, maxCritical = 50.0)
    @StatVariable
    @Getter @Setter
    private Double albumin;

    @Indicator
    @Column(name = "CK")
    @DisplayName("Креатинфосфокиназа")
    @IndicatorNorm(minCritical = 14.0, min = 34.0, max = 170.0, maxCritical = 210.0)
    @StatVariable
    @Getter @Setter
    private Double ck;

    @Indicator
    @Column(name = "CKMB")
    @DisplayName("Креатинфосфокиназа МВ")
    @IndicatorNorm(minCritical = 0.0, min = 0.0, max = 22.0, maxCritical = 28.0)
    @StatVariable
    @Getter @Setter
    private Double ckmb;

    @Indicator
    @Column(name = "LDH")
    @DisplayName("myЛактатдкгидрогеназа")
    @IndicatorNorm(minCritical = 195.0, min = 225.0, max = 420.0, maxCritical = 480.0)
    @StatVariable
    @Getter @Setter
    private Double ldh;

    @Indicator
    @Column(name = "GGT")
    @DisplayName("ГГТ")
    @IndicatorNorm(minCritical = 6.0, min = 16.0, max = 56.0, maxCritical = 66.0)
    @StatVariable
    @Getter @Setter
    private Double ggt;

    @Indicator
    @DisplayName("Амилаза")
    @IndicatorNorm(minCritical = 23.0, min = 33.0, max = 90.0, maxCritical = 110.0)
    @StatVariable
    @Getter @Setter
    private Double amylase;

    @Indicator
    @DisplayName("Пакриатическая амилаза")
    @IndicatorNorm(minCritical = 0.0, min = 0.0, max = 48.0, maxCritical = 58.0)
    @StatVariable
    @Getter @Setter
    private Double pancrAmylase;

    @Indicator
    @DisplayName("Глюкоза")
    @IndicatorNorm(minCritical = 3.7, min = 4.7, max = 5.9, maxCritical = 6.9)
    @StatVariable
    @Getter @Setter
    private Double glucose;

    @Indicator
    @DisplayName("Мочевая кислота")
    @IndicatorNorm(minCritical = 150.0, min = 250.0, max = 370.0, maxCritical = 470.0)
    @StatVariable
    @Getter @Setter
    private Double ureaAcid;

    @Indicator
    @Column(name = "CRP")
    @DisplayName("С-реактивный белок")
    @IndicatorNorm(minCritical = 0.0, min = 0.0, max = 4.5, maxCritical = 5.5)
    @StatVariable
    @Getter @Setter
    private Double crp;

    @Indicator
    @Column(name = "RF")
    @DisplayName("Ревматоидный фактор")
    @IndicatorNorm(minCritical = 0.0, min = 0.0, max = 35.0, maxCritical = 45.0)
    @StatVariable
    @Getter @Setter
    private Double rf;

    @Indicator
    @DisplayName("Калий")
    @IndicatorNorm(minCritical = 3.0, min = 4.0, max = 4.6, maxCritical = 5.6)
    @StatVariable
    @Getter @Setter
    private Double potassium;

    @Indicator
    @DisplayName("Натрий")
    @IndicatorNorm(minCritical = 134.0, min = 138.0, max = 143.0, maxCritical = 147.0)
    @StatVariable
    @Getter @Setter
    private Double sodium;

    @Indicator
//    @Column(name = "chlorine")
    @DisplayName("Хлор")
    @IndicatorNorm(minCritical = 96.0, min = 100.0, max = 105.0, maxCritical = 109.0)
    @StatVariable
    @Getter @Setter
    private Double chlorine;

    @Indicator
//    @Column(name = "commonCalcium")
    @DisplayName("Общий кальций")
    @IndicatorNorm(minCritical = 1.92, min = 2.2, max = 2.5, maxCritical = 2.7)
    @StatVariable
    @Getter @Setter
    private Double commonCalcium;

    @Indicator
//    @Column(name = "ionCalcium")
    @DisplayName("Ионизированный кальций")
    @IndicatorNorm(minCritical = 1.11, min = 1.21, max = 1.27, maxCritical = 1.37)
    @StatVariable
    @Getter @Setter
    private Double ionCalcium;

    @Indicator
//    @Column(name = "phosphor")
    @DisplayName("Фосфор")
    @IndicatorNorm(minCritical = 0.71, min = 0.92, max = 1.52, maxCritical = 1.72)
    @StatVariable
    @Getter @Setter
    private Double phosphor;

    @Indicator
//    @Column(name = "ferrum")
    @DisplayName("Железо")
    @IndicatorNorm(minCritical = 8.7, min = 12.7, max = 26.6, maxCritical = 30.6)
    @StatVariable
    @Getter @Setter
    private Double ferrum;

    @Indicator
//    @Column(name = "ferritin")
    @DisplayName("Ферритин")
    @IndicatorNorm(minCritical = 26.5, min = 36.5, max = 211.7, maxCritical = 251.7)
    @StatVariable
    @Getter @Setter
    private Double ferritin;
}
