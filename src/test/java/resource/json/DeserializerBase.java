package resource.json;

import com.anigenero.microservice.resource.provider.ObjectMapperResolverProvider;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.StandardCharsets;

public abstract class DeserializerBase<T extends JsonDeserializer<V>, V> {

    private final Class<T> serializerClass;

    private T serializer;
    private ObjectMapper mapper;

    @SuppressWarnings("unchecked")
    public DeserializerBase() {
        this.serializerClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Before
    public void setup() {
        this.mapper = new ObjectMapperResolverProvider().getContext(null);
        try {
            this.serializer = this.serializerClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            Assert.fail(e.getMessage());
        }
    }

    protected T getDeserializer() {
        return serializer;
    }

    protected ObjectMapper getMapper() {
        return mapper;
    }

    protected V deserialize(String jsonString) throws IOException {
        return deserialize(jsonString, 3);
    }

    protected V deserialize(String jsonString, int iterations) throws IOException {

        InputStream stream = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
        JsonParser parser = getMapper().getFactory().createParser(stream);
        DeserializationContext context = getMapper().getDeserializationContext();

        for (int i = 0; i < iterations; i++) {
            parser.nextToken();
        }

        return getDeserializer().deserialize(parser, context);

    }

}
