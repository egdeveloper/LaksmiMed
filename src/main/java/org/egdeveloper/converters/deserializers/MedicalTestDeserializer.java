package org.egdeveloper.converters.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.egdeveloper.attributes.Indicator;
import org.egdeveloper.data.model.MedicalTest;
import org.egdeveloper.data.model.customTypes.PatientState;
import org.egdeveloper.data.model.customTypes.TreatmentNumber;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.lang.reflect.Field;

public class MedicalTestDeserializer extends JsonDeserializer<MedicalTest> {

    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
    @Override
    public MedicalTest deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        try {
            ObjectCodec objectCodec = jp.getCodec();
            JsonNode node = objectCodec.readTree(jp);
            Class mtClazz = Class.forName(node.get("type").asText());
            MedicalTest test = (MedicalTest) mtClazz.newInstance();
            test.setId(node.get("id").asInt());
            test.setTestDate(LocalDate.parse(node.get("testDate").asText(), formatter));
            test.setDescription(node.get("description").asText());
            test.setPatientState(PatientState.valueOf(node.get("patientState").asText()));
            test.setTreatment(node.get("treatment").asText());
            test.setTreatmentNumber(TreatmentNumber.valueOf(node.get("treatmentNumber").asText()));
            for(Field field : mtClazz.getDeclaredFields()){
                if(field.isAnnotationPresent(Indicator.class)){
                    field.setAccessible(true);
                    if(field.getType().isAssignableFrom(Double.class)){
                        if(!node.get(field.getName()).isNull()){
                            field.set(test, node.get(field.getName()).asDouble());
                        }
                        else{
                            field.set(test, null);
                        }
                    }
                    else if(field.getType().isAssignableFrom(String.class)){
                        field.set(test, node.get(field.getName()).asText());
                    }
                    else if(field.getType().isAssignableFrom(Enum.class)){
                        field.set(test, Enum.valueOf((Class<? extends Enum>) field.getType(), node.get(field.getName()).asText()));
                    }
                }
            }
            return test;
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException exception){
            throw new RuntimeException(exception);
        }
    }
}
