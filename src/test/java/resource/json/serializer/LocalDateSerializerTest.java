package resource.json.serializer;

import com.anigenero.microservice.resource.json.serializer.LocalDateSerializer;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.Test;
import resource.json.SerializerBase;

import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class LocalDateSerializerTest extends SerializerBase<LocalDateSerializer, LocalDate> {

    @Test
    void serialize() throws Exception {

        Writer jsonWriter = new StringWriter();
        JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
        SerializerProvider serializerProvider = getMapper().getSerializerProvider();

        getSerializer().serialize(LocalDate.of(2017, Month.JANUARY, 1), jsonGenerator, serializerProvider);

        jsonGenerator.flush();

        assertThat(jsonWriter.toString()).isEqualTo("\"2017-01-01\"");

    }

}