package ru.venediktov.testspringproject.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ZoneDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

    @Override
    public void serialize(ZonedDateTime value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        String dateString = null;

        if (value != null) {
            dateString = value.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        }

        jgen.writeString(dateString);
    }
}
