package org.egdeveloper.converters.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.egdeveloper.data.model.Patient;
import org.egdeveloper.data.model.customTypes.*;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class PatientDeserializer extends JsonDeserializer<Patient> {

    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");

    @Override
    public Patient deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Patient patient = new Patient();
        ObjectCodec objectCodec = jp.getCodec();
        JsonNode node = objectCodec.readTree(jp);
        patient.setId(node.get("id").asInt());
        patient.setFullName(node.get("fullName").asText());
        patient.setGender(Gender.valueOf(node.get("gender").asText()));
        patient.setBirthdate(LocalDate.parse(node.get("birthdate").asText(), formatter));
        patient.setCardNumber(node.get("cardNumber").asText());
        patient.setPassport(node.get("passport").asText());
        patient.setPhoneNumber(node.get("phoneNumber").asText());
        patient.setCountry(node.get("country").asText());
        patient.setPostIndex(node.get("postIndex").asText());
        patient.setRegion(node.get("region").asText());
        patient.setCity(node.get("city").asText());
        patient.setAddress(node.get("address").asText());
        patient.setEmail(node.get("email").asText());
        patient.setPatientState(PatientState.valueOf(node.get("patientState").asText()));
        patient.setRh(Rh.valueOf(node.get("rh").asText()));
        patient.setBloodGroup(BloodGroup.valueOf(node.get("bloodGroup").asText()));
        patient.setDisability(Disability.valueOf(node.get("disability").asText()));
        patient.setTIN(node.get("tin").asText());
        patient.setOMICard(node.get("omiCard").asText());
        patient.setJobPlace(node.get("jobPlace").asText());
        patient.setOccupation(node.get("occupation").asText());
        patient.setJobPost(node.get("jobPost").asText());
        patient.setJobConditions(node.get("jobConditions").asText());
        patient.setComplaints(node.get("complaints").asText());
        patient.setPremedication(node.get("premedication").asText());
        patient.setAssociatedDisease(node.get("associatedDisease").asText());
        patient.setPreMedicalSupplies(node.get("preMedicalSupplies").asText());
        patient.setBadHabits(node.get("badHabits").asText());
        patient.setPreUreaStoneDescription(node.get("preUreaStoneDescription").asText());
        patient.setDiseaseDuration(node.get("diseaseDuration").asText());
        return patient;
    }
}
