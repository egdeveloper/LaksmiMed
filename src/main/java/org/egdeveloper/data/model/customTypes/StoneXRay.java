package org.egdeveloper.data.model.customTypes;


public enum StoneXRay {
    OXALATES("оксалаты"),
    URATES("ураты"),
    PHOSPHATES("фосфаты"),
    OXALATES_URATES("оксалаты + ураты"),
    OXALATES_PHOSPHATES("оксалаты + фосфаты"),
    URATES_PHOSPHATES("ураты + фосфаты"),
    OXALATES_URATES_PHOSPHATES("оксалаты + ураты + фосфаты");
    private String xray;
    StoneXRay(String xray){
        this.xray = xray;
    }
    public String stoneXray2String(){
        return xray;
    }
}
