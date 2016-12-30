package org.egdeveloper.converters.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.egdeveloper.data.model.StoneInVivoTest;

import java.io.IOException;

public class StoneInVivoTestDeserializer extends MedicalTestDeserializer {

    @Override
    public StoneInVivoTest deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
        return (StoneInVivoTest) super.deserialize(jp, deserializationContext);
    }
}
