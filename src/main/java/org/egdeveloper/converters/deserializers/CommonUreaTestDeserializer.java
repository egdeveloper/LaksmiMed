package org.egdeveloper.converters.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.egdeveloper.data.model.CommonUreaTest;

import java.io.IOException;

public class CommonUreaTestDeserializer extends MedicalTestDeserializer {

    @Override
    public CommonUreaTest deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
        return (CommonUreaTest) super.deserialize(jp, deserializationContext);
    }
}
