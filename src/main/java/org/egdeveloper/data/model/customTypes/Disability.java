package org.egdeveloper.data.model.customTypes;

/**
 * Created by egdeveloper on 17.11.15.
 */
public enum Disability {
    I("I"), II("II"), III("III"), NONE("нет");
    private String disability;
    private Disability(String disability){
        this.disability = disability;
    }
    public String disability2String(){
        return disability;
    }
}
