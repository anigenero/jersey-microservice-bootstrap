package resource.json;

import com.anigenero.microservice.resource.provider.ObjectMapperResolverProvider;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class DeserializerBase<T extends JsonDeserializer<V>, V> {

    private final Class<T> deserializerClass;

    private T deserializer;
    private ObjectMapper mapper;

    @SuppressWarnings({"unchecked", "WeakerAccess"})
    public DeserializerBase() {
        this.deserializerClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @BeforeEach
    public void setup() {
        this.mapper = new ObjectMapperResolverProvider().getContext(null);
        try {
            this.deserializer = this.deserializerClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            fail(e.getMessage());
        }
    }

    protected V deserialize(String jsonString) throws IOException {
        return deserialize(jsonString, 3);
    }

    @SuppressWarnings({"SameParameterValue","WeakerAccess"})
    protected V deserialize(String jsonString, int iterations) throws IOException {

        InputStream stream = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
        JsonParser parser = this.mapper.getFactory().createParser(stream);
        DeserializationContext context = this.mapper.getDeserializationContext();

        for (int i = 0; i < iterations; i++) {
            parser.nextToken();
        }

        return this.deserializer.deserialize(parser, context);

    }

}
