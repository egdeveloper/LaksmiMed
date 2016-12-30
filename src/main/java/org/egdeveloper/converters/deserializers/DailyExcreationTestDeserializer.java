package org.egdeveloper.converters.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.egdeveloper.data.model.DailyExcreationTest;

import java.io.IOException;

public class DailyExcreationTestDeserializer extends MedicalTestDeserializer {

    @Override
    public DailyExcreationTest deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
        return (DailyExcreationTest) super.deserialize(jp, deserializationContext);
    }
}
