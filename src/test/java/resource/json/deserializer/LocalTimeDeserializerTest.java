package resource.json.deserializer;

import com.anigenero.microservice.resource.json.deserializer.LocalTimeDeserializer;
import org.junit.jupiter.api.Test;
import resource.json.DeserializerBase;

import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LocalTimeDeserializerTest extends DeserializerBase<LocalTimeDeserializer, LocalTime> {

    @Test
    void deserializeString() throws Exception {

        final String json = "{ \"value\" : \"12:34:01\" }";
        LocalTime localTime = deserialize(json);

        assertThat(localTime.getHour()).isEqualTo(12);
        assertThat(localTime.getMinute()).isEqualTo(34);
        assertThat(localTime.getSecond()).isEqualTo(1);

    }

    @Test
    void deserializeNanoDay() throws Exception {

        final String json = "{ \"value\" : 57272 }";
        LocalTime localTime = deserialize(json);

        assertThat(localTime.getHour()).isEqualTo(15);
        assertThat(localTime.getMinute()).isEqualTo(54);
        assertThat(localTime.getSecond()).isEqualTo(32);

    }

}