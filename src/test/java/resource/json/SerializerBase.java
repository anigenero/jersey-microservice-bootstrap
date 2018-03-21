package resource.json;

import com.anigenero.microservice.resource.provider.ObjectMapperResolverProvider;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.ParameterizedType;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class SerializerBase<T extends JsonSerializer<V>, V> {

    private final Class<T> serializerClass;

    private T serializer;
    private ObjectMapper mapper;

    @SuppressWarnings({"unchecked", "WeakerAccess"})
    public SerializerBase() {
        this.serializerClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @BeforeEach
    public void setup() {
        this.mapper = new ObjectMapperResolverProvider().getContext(null);
        try {
            this.serializer = this.serializerClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            fail(e.getMessage());
        }
    }

    protected T getSerializer() {
        return serializer;
    }

    protected ObjectMapper getMapper() {
        return mapper;
    }

}
