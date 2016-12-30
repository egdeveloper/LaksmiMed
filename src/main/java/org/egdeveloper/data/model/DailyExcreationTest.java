package org.egdeveloper.data.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.egdeveloper.attributes.*;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table
@MedTest(DailyExcreationTest.class)
@EntityID("dailyExcreation")
@DisplayName("Суточная экскреция")
@JsonSerialize(as = DailyExcreationTest.class)
@JsonDeserialize(as = DailyExcreationTest.class)
@EqualsAndHashCode(callSuper = true)
@ToString
public class DailyExcreationTest extends MedicalTest {

    public DailyExcreationTest(){
        super();
    }

    @Indicator
    @DisplayName("Креатинин")
    @IndicatorNorm(minCritical = 3.9, min = 4.9, max = 15.6, maxCritical = 19.6)
    @StatVariable
    @Getter @Setter
    private Double creatinine;

    @Indicator
    @DisplayName("Мочевина")
    @IndicatorNorm(minCritical = 303.0, min = 363.0, max = 522.8, maxCritical = 642.8)
    @StatVariable
    @Getter @Setter
    private Double urea;

    @Indicator
    @DisplayName("Мочевая кислота")
    @IndicatorNorm(minCritical = 1.42, min = 1.62, max = 3.30, maxCritical = 3.9)
    @StatVariable
    @Getter @Setter
    private Double ureaAcid;

    @Indicator
    @DisplayName("Кальций")
    @IndicatorNorm(minCritical = 2.3, min = 2.7, max = 6.8, maxCritical = 8.2)
    @StatVariable
    @Getter @Setter
    private Double calcium;

    @Indicator
    @DisplayName("Калий")
    @IndicatorNorm(minCritical = 27.0, min = 33.0, max = 90.0, maxCritical = 110.0)
    @StatVariable
    @Getter @Setter
    private Double potassium;

    @Indicator
    @DisplayName("Магний")
    @IndicatorNorm(minCritical = 0.45, min = 0.55, max = 3.7, maxCritical = 4.7)
    @StatVariable
    @Getter @Setter
    private Double magnesium;

    @Indicator
    @DisplayName("Натрий")
    @IndicatorNorm(minCritical = 120.0, min = 140.0, max = 230.0, maxCritical = 290.0)
    @StatVariable
    @Getter @Setter
    private Double sodium;

    @Indicator
    @DisplayName("Оксид Фосфора")
    @IndicatorNorm(minCritical = 11.4, min = 14.4, max = 35.0, maxCritical = 45.0)
    @StatVariable
    @Getter @Setter
    private Double phosphorOxide;

    @Indicator
    @DisplayName("Хлор")
    @IndicatorNorm(minCritical = 99.0, min = 121.0, max = 225.0, maxCritical = 275.0)
    @StatVariable
    @Getter @Setter
    private Double chlorine;

}
