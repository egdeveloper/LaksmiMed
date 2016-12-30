package org.egdeveloper.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.egdeveloper.converters.deserializers.DoctorDeserializer;
import org.egdeveloper.converters.deserializers.MedicalTestDeserializer;
import org.egdeveloper.converters.deserializers.PatientDeserializer;
import org.egdeveloper.converters.serializers.DoctorSerializer;
import org.egdeveloper.converters.serializers.MedicalTestSerializer;
import org.egdeveloper.converters.serializers.PatientSerializer;
import org.egdeveloper.data.model.Doctor;
import org.egdeveloper.data.model.MedicalTest;
import org.egdeveloper.data.model.Patient;
import org.springframework.stereotype.Component;

//@Component("customJSONMapper")
public class CustomJSONMapper extends ObjectMapper {

    public CustomJSONMapper(){
        SimpleModule module = new SimpleModule();
        module.addSerializer(Doctor.class, new DoctorSerializer());
        module.addDeserializer(Doctor.class, new DoctorDeserializer());
        module.addSerializer(Patient.class, new PatientSerializer());
        module.addDeserializer(Patient.class, new PatientDeserializer());
        MedicalTestSerializer medicalTestSerializer = new MedicalTestSerializer();
        MedicalTestDeserializer medicalTestDeserializer = new MedicalTestDeserializer();
        module.addSerializer(MedicalTest.class, medicalTestSerializer);
        module.addDeserializer(MedicalTest.class, medicalTestDeserializer);
        registerModule(module);
    }
}
