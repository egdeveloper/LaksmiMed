package org.egdeveloper.converters.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.egdeveloper.data.model.StoneInVitroTest;

import java.io.IOException;

public class StoneInVitroTestDeserializer extends MedicalTestDeserializer {

    @Override
    public StoneInVitroTest deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
        return (StoneInVitroTest) super.deserialize(jp, deserializationContext);
    }
}
