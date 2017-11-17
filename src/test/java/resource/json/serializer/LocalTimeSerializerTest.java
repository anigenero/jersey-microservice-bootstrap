package resource.json.serializer;

import com.anigenero.microservice.resource.json.serializer.LocalTimeSerializer;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Test;
import resource.json.SerializerBase;

import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class LocalTimeSerializerTest extends SerializerBase<LocalTimeSerializer, LocalTime> {

    @Test
    public void serialize() throws Exception {

        Writer jsonWriter = new StringWriter();
        JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
        SerializerProvider serializerProvider = getMapper().getSerializerProvider();

        getSerializer().serialize(LocalTime.of(13, 1, 23), jsonGenerator, serializerProvider);

        jsonGenerator.flush();

        assertThat(jsonWriter.toString()).isEqualTo("13:01:23");

    }

}