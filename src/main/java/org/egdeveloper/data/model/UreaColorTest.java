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
@MedTest(UreaColorTest.class)
@EntityID("ureaColor")
@DisplayName("Хроматография")
@JsonSerialize(as = UreaColorTest.class)
@JsonDeserialize(as = UreaColorTest.class)
@EqualsAndHashCode(callSuper = true)
@ToString
public class UreaColorTest extends MedicalTest {

    public UreaColorTest(){
        super();
    }

    @Indicator
    @Column(name = "DUV")
    @DisplayName("Диурез")
    @IndicatorNorm(minCritical = 720, min = 880, max = 1350, maxCritical = 1650)
    @StatVariable
    @Getter @Setter
    private Double duv;

    @Indicator
    @Column(name = "ClSalt")
    @DisplayName("Хлорид")
    @IndicatorNorm(minCritical = 160, min = 200, max = 252, maxCritical = 312)
    @StatVariable
    @Getter @Setter
    private Double clSalt;

    @Indicator
    @Column(name = "NO2Salt")
    @DisplayName("Нитрит")
    @IndicatorNorm(minCritical = 0, min = 0, max = 0, maxCritical = 0.05)
    @StatVariable
    @Getter @Setter
    private Double no2Salt;

    @Indicator
    @Column(name = "NO3Salt")
    @DisplayName("Нитрат")
    @IndicatorNorm(minCritical = 0.7, min = 0.9, max = 2.06, maxCritical = 2.46)
    @StatVariable
    @Getter @Setter
    private Double no3Salt;

    @Indicator
    @Column(name = "SO3Salt")
    @DisplayName("Сульфат")
    @IndicatorNorm(minCritical = 23.6, min = 29.6, max = 38.0, maxCritical = 46.0)
    @StatVariable
    @Getter @Setter
    private Double so3Salt;

    @Indicator
    @Column(name = "PO3Salt")
    @DisplayName("Фосфат")
    @IndicatorNorm(minCritical = 9.5, min = 11.5, max = 62.0, maxCritical = 74.0)
    @StatVariable
    @Getter @Setter
    private Double po3Salt;

    @Indicator
    @Column(name = "citrate")
    @DisplayName("Цитрат")
    @IndicatorNorm(minCritical = 2.2, min = 2.6, max = 4.8, maxCritical = 5.8)
    @StatVariable
    @Getter @Setter
    private Double citrate;

    @Indicator
    @Column(name = "isoCitrate")
    @DisplayName("Изоцитрат")
    @IndicatorNorm(minCritical = 0.63, min = 0.77, max = 1.8, maxCritical = 2.2)
    @StatVariable
    @Getter @Setter
    private Double isoCitrate;

    @Indicator
    @Column(name = "ureaAcid")
    @DisplayName("Мочевая кислота")
    @IndicatorNorm(minCritical = 5.9, min = 7.1, max = 11.7, maxCritical = 14.3)
    @StatVariable
    @Getter @Setter
    private Double ureaAcid;
}
