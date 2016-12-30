package org.egdeveloper.converters.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.egdeveloper.data.model.UreaColorTest;

import java.io.IOException;

public class UreaColorTestDeserializer extends MedicalTestDeserializer {

    @Override
    public UreaColorTest deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
        return (UreaColorTest) super.deserialize(jp, deserializationContext);
    }
}
