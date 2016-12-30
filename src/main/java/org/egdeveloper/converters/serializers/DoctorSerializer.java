package org.egdeveloper.converters.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.egdeveloper.data.model.Doctor;

import java.io.IOException;


public class DoctorSerializer extends JsonSerializer<Doctor> {

    @Override
    public void serialize(Doctor doctor, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", Doctor.class.getName());
        jsonGenerator.writeNumberField("id", doctor.getId());
        jsonGenerator.writeStringField("fullName", doctor.getFullName());
        jsonGenerator.writeStringField("login", doctor.getLogin());
        jsonGenerator.writeStringField("password", doctor.getPassword());
        jsonGenerator.writeStringField("email", doctor.getEmail());
        jsonGenerator.writeStringField("phoneNumber", doctor.getPhoneNumber());
        jsonGenerator.writeStringField("jobPost", doctor.getJobPost());
        jsonGenerator.writeStringField("jobPlace", doctor.getJobPlace());
        jsonGenerator.writeEndObject();
    }
}
