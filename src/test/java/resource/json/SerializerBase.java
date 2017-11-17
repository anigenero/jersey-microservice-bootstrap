package resource.json;

import com.anigenero.microservice.resource.provider.ObjectMapperResolverProvider;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;

import java.lang.reflect.ParameterizedType;

public abstract class SerializerBase<T extends JsonSerializer<V>, V> {

    private final Class<T> serializerClass;

    private T serializer;
    private ObjectMapper mapper;

    @SuppressWarnings("unchecked")
    public SerializerBase() {
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

    protected T getSerializer() {
        return serializer;
    }

    protected ObjectMapper getMapper() {
        return mapper;
    }

}
