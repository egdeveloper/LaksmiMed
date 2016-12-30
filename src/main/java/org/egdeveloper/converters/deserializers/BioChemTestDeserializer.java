package org.egdeveloper.converters.deserializers;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.egdeveloper.data.model.BioChemTest;

import java.io.IOException;

public class BioChemTestDeserializer extends MedicalTestDeserializer {

    @Override
    public BioChemTest deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return (BioChemTest) super.deserialize(jp, deserializationContext);
    }
}
