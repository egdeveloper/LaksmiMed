package org.egdeveloper.data.model.customTypes;

/**
 * Created by egdeveloper on 17.11.15.
 */
public enum BloodGroup {
    I("I"), II("II"), III("III"), IV("IV");
    private String blood_group;
    private BloodGroup(String blood_group){
        this.blood_group = blood_group;
    }
    public String bloodGroup2String(){
        return blood_group;
    }
}
