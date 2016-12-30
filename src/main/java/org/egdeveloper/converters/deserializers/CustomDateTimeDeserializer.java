package org.egdeveloper.converters.deserializers;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class CustomDateTimeDeserializer extends JsonDeserializer<LocalDate> {

    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException{
        String dateStr = null;
        String fieldName = null;

        while (jp.hasCurrentToken()) {
            JsonToken token = jp.nextToken();
            if (token == JsonToken.FIELD_NAME) {
                fieldName = jp.getCurrentName();
            } else if (token == JsonToken.VALUE_STRING) {
                if (StringUtils.equals(fieldName, "birthdate")) {
                    dateStr = jp.getValueAsString();
                }
            } else if (token == JsonToken.END_OBJECT) {
                break;
            }
        }
        if (dateStr != null) {
            LocalDate dateTime = LocalDate.parse(dateStr + " ", formatter);
            return dateTime;
        }
        return null;
    }
}
