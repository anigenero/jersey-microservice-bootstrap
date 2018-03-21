package resource.json.deserializer;

import com.anigenero.microservice.resource.json.deserializer.LocalDateDeserializer;
import org.junit.jupiter.api.Test;
import resource.json.DeserializerBase;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LocalDateDeserializerTest extends DeserializerBase<LocalDateDeserializer, LocalDate> {

    @Test
    void deserializeString() throws Exception {

        final String json = "{ \"value\" : \"2017-04-16\" }";
        LocalDate localDate = deserialize(json);

        assertThat(localDate.getYear()).isEqualTo(2017);
        assertThat(localDate.getMonth()).isEqualTo(Month.APRIL);
        assertThat(localDate.getDayOfMonth()).isEqualTo(16);

    }

    @Test
    void deserializeEpochDay() throws Exception {

        final String json = "{ \"value\" : 17272 }";
        LocalDate localDate = deserialize(json);

        assertThat(localDate.getYear()).isEqualTo(2017);
        assertThat(localDate.getMonth()).isEqualTo(Month.APRIL);
        assertThat(localDate.getDayOfMonth()).isEqualTo(16);

    }

    @Test
    void testNullValue() throws Exception {

        final String json = "{ \"value\" : null }";
        LocalDate localDate = deserialize(json);

        assertThat(localDate).isNull();

    }

}