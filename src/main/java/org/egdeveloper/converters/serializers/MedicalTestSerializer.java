package org.egdeveloper.converters.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.egdeveloper.attributes.Indicator;
import org.egdeveloper.data.model.MedicalTest;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.lang.reflect.Field;

public class MedicalTestSerializer extends JsonSerializer<MedicalTest> {

    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");

    @Override
    public void serialize(MedicalTest medicalTest, JsonGenerator jg, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        try {
            Class mtClazz = medicalTest.getClass();
            jg.writeStartObject();
            jg.writeStringField("@class", mtClazz.getName());
            jg.writeStringField("type", mtClazz.getName());
            jg.writeNumberField("id", medicalTest.getId());
            jg.writeStringField("testDate", formatter.print(medicalTest.getTestDate()));
            jg.writeStringField("treatment", medicalTest.getTreatment());
            jg.writeStringField("treatmentNumber", medicalTest.getTreatmentNumber().toString());
            jg.writeStringField("description", medicalTest.getDescription());
            jg.writeStringField("patientState", medicalTest.getPatientState().toString());
            for (Field field : mtClazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Indicator.class)) {
                    field.setAccessible(true);
                    if (field.getType().isAssignableFrom(Double.class)) {
                        Double indicatorValue = (Double) field.get(medicalTest);
                        if(indicatorValue != null)
                            jg.writeNumberField(field.getName(), (Double) field.get(medicalTest));
                        else
                            jg.writeNullField(field.getName());
                    }
                    if(field.getType().isAssignableFrom(String.class)){
                        jg.writeStringField(field.getName(), (String) field.get(medicalTest));
                    }
                    if(field.getType().isAssignableFrom(Enum.class)){
                        jg.writeStringField(field.getName(), field.get(medicalTest).toString());
                    }
                }
            }
            jg.writeEndObject();
        }
        catch (IllegalAccessException exception){
            throw new RuntimeException(exception);
        }
    }
}
