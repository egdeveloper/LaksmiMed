package org.egdeveloper.utils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Developer: egdeveloper
 * Creation-Date: 15.08.16
 */

@EqualsAndHashCode
@ToString
public class MedTestMeta {

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String entityID;

    @Getter @Setter
    private Class testClass;

    @Getter @Setter
    private List<IndicatorMeta> indicators = new ArrayList<>();
}
