package org.egdeveloper.converters.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.egdeveloper.data.model.Doctor;

import java.io.IOException;

public class DoctorDeserializer extends JsonDeserializer<Doctor> {
    @Override
    public Doctor deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Doctor doctor = new Doctor();
        ObjectCodec objectCodec = jp.getCodec();
        JsonNode node = objectCodec.readTree(jp);
        doctor.setId(node.get("id").asInt());
        doctor.setFullName(node.get("fullName").asText());
        doctor.setLogin(node.get("login").asText());
        doctor.setPassword(node.get("password").asText());
        doctor.setPhoneNumber(node.get("phoneNumber").asText());
        doctor.setEmail(node.get("email").asText());
        doctor.setJobPost(node.get("jobPost").asText());
        doctor.setJobPlace(node.get("jobPlace").asText());
        return doctor;
    }
}
