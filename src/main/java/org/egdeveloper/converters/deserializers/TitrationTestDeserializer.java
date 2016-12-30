package org.egdeveloper.converters.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.egdeveloper.data.model.TitrationTest;

import java.io.IOException;

public class TitrationTestDeserializer extends MedicalTestDeserializer {

    @Override
    public TitrationTest deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
        return (TitrationTest) super.deserialize(jp, deserializationContext);
    }
}
