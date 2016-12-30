package org.egdeveloper.converters.deserializers;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.egdeveloper.data.model.CommonBloodTest;

import java.io.IOException;

public class CommonBloodTestDeserializer extends MedicalTestDeserializer {

    @Override
    public CommonBloodTest deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
        return (CommonBloodTest) super.deserialize(jp, deserializationContext);
    }
}
