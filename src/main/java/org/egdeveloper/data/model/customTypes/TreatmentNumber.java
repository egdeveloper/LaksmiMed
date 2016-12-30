package org.egdeveloper.data.model.customTypes;


public enum TreatmentNumber {
    NONE("не было"),
    I("лечени 1"),
    II("лечение 2"),
    III("лечение 3"),
    IV("лечение 4"),
    V("лечение 5"),
    VI("лечение 6"),
    VII("лечение 7"),
    VIII("лечение 8"),
    IX("лечение 9"),
    X("лечение 10"),
    XI("лечение 11"),
    XII("лечение 12");

    private String treatmentNumber;
    TreatmentNumber(String treatmentNumber){
        this.treatmentNumber = treatmentNumber;
    }
}
