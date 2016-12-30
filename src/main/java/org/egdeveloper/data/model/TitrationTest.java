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
@MedTest(TitrationTest.class)
@EntityID("titration")
@DisplayName("Титриметрия")
@JsonSerialize(as = TitrationTest.class)
@JsonDeserialize(as = TitrationTest.class)
@EqualsAndHashCode(callSuper = true)
@ToString
public class TitrationTest extends MedicalTest {

    public TitrationTest(){
        super();
    }

    @Indicator
    @Column(name = "oxalate", nullable = true)
    @DisplayName("Оксалат")
    @IndicatorNorm(minCritical = 25, min = 35, max = 55, maxCritical = 65)
    @StatVariable
    @Getter @Setter
    private Double oxalate;
}
