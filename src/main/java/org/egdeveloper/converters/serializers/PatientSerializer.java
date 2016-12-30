package org.egdeveloper.converters.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.egdeveloper.data.model.Patient;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class PatientSerializer extends JsonSerializer<Patient> {

    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");

    @Override
    public void serialize(Patient patient, JsonGenerator jg, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jg.writeStartObject();
        jg.writeStringField("type", Patient.class.getName());
        jg.writeNumberField("id", patient.getId());
        jg.writeStringField("fullName", patient.getFullName());
        jg.writeStringField("gender", patient.getGender().toString());
        jg.writeStringField("birthdate", formatter.print(patient.getBirthdate()));
        jg.writeStringField("cardNumber", patient.getCardNumber());
        jg.writeStringField("passport", patient.getPassport());
        jg.writeStringField("phoneNumber", patient.getPhoneNumber());
        jg.writeStringField("country", patient.getCountry());
        jg.writeStringField("postIndex", patient.getPostIndex());
        jg.writeStringField("region", patient.getRegion());
        jg.writeStringField("city", patient.getCity());
        jg.writeStringField("address", patient.getAddress());
        jg.writeStringField("email", patient.getEmail());
        jg.writeStringField("patientState", patient.getPatientState().toString());
        jg.writeStringField("rh", patient.getRh().toString());
        jg.writeStringField("bloodGroup", patient.getBloodGroup().toString());
        jg.writeStringField("disability", patient.getDisability().toString());
        jg.writeStringField("TIN", patient.getTIN());
        jg.writeStringField("OMICard", patient.getOMICard());
        jg.writeStringField("jobPlace", patient.getJobPlace());
        jg.writeStringField("occupation", patient.getOccupation());
        jg.writeStringField("jobPost", patient.getJobPost());
        jg.writeStringField("jobConditions", patient.getJobConditions());
        jg.writeStringField("complaints", patient.getComplaints());
        jg.writeStringField("premedication", patient.getPremedication());
        jg.writeStringField("associatedDisease", patient.getAssociatedDisease());
        jg.writeStringField("preMedicalSupplies", patient.getPreMedicalSupplies());
        jg.writeStringField("badHabits", patient.getBadHabits());
        jg.writeStringField("preUreaStoneDescription", patient.getPreUreaStoneDescription());
        jg.writeStringField("diseaseDuration", patient.getDiseaseDuration());
        jg.writeEndObject();
    }
}
