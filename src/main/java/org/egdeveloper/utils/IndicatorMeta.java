package org.egdeveloper.utils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Developer: egdeveloper
 * Creation-Date: 15.08.16
 */

@EqualsAndHashCode
@ToString
public class IndicatorMeta {

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String displayName;

    @Getter @Setter
    private Double min;

    @Getter @Setter
    private Double max;
}
