package resource.json.deserializer;

import com.anigenero.microservice.resource.json.deserializer.LocalDateTimeDeserializer;
import org.junit.jupiter.api.Test;
import resource.json.DeserializerBase;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LocalDateTimeDeserializerTest extends DeserializerBase<LocalDateTimeDeserializer, LocalDateTime> {

    @Test
    void deserializeString() throws Exception {

        final String json = "{ \"value\" : \"2017-04-16T12:34:01\" }";
        LocalDateTime localDate = deserialize(json);

        assertThat(localDate.getYear()).isEqualTo(2017);
        assertThat(localDate.getMonth()).isEqualTo(Month.APRIL);
        assertThat(localDate.getDayOfMonth()).isEqualTo(16);
        assertThat(localDate.getHour()).isEqualTo(12);
        assertThat(localDate.getMinute()).isEqualTo(34);
        assertThat(localDate.getSecond()).isEqualTo(1);

    }

    @Test
    void deserializeEpochTime() throws Exception {

        final LocalDateTime value = LocalDateTime.of(2017, Month.AUGUST, 4, 15, 40, 16);
        Instant instant = value.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        final String json = "{ \"value\" : " + date.getTime() + " }";
        LocalDateTime localDate = deserialize(json);

        assertThat(localDate.getYear()).isEqualTo(2017);
        assertThat(localDate.getMonth()).isEqualTo(Month.AUGUST);
        assertThat(localDate.getDayOfMonth()).isEqualTo(4);
        assertThat(localDate.getHour()).isEqualTo(15);
        assertThat(localDate.getMinute()).isEqualTo(40);
        assertThat(localDate.getSecond()).isEqualTo(16);

    }

    @Test
    void nullValue() throws Exception {

        final String json = "{ \"value\" : null }";
        LocalDateTime localDate = deserialize(json);

        assertThat(localDate).isNull();

    }

}