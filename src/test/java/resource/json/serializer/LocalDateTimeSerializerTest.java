package resource.json.serializer;

import com.anigenero.microservice.resource.json.serializer.LocalDateTimeSerializer;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Test;
import resource.json.SerializerBase;

import java.io.StringWriter;
import java.io.Writer;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class LocalDateTimeSerializerTest extends SerializerBase<LocalDateTimeSerializer, LocalDateTime> {

    @Test
    public void serialize() throws Exception {

        Writer jsonWriter = new StringWriter();
        JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
        SerializerProvider serializerProvider = getMapper().getSerializerProvider();

        final LocalDateTime value = LocalDateTime.of(1990, Month.JANUARY, 11, 2, 40, 15);
        getSerializer().serialize(value, jsonGenerator, serializerProvider);

        jsonGenerator.flush();

        Instant instant = value.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        assertThat(jsonWriter.toString()).isEqualTo(Long.toString(date.getTime()));

    }

    @Test
    public void serializeNull() throws Exception {

        Writer jsonWriter = new StringWriter();
        JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
        SerializerProvider serializerProvider = getMapper().getSerializerProvider();

        getSerializer().serialize(null, jsonGenerator, serializerProvider);

        assertThat(jsonWriter.toString()).isEqualTo("");

    }

}