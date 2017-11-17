package com.anigenero.microservice.resource.provider;

import com.anigenero.microservice.resource.json.deserializer.LocalDateDeserializer;
import com.anigenero.microservice.resource.json.deserializer.LocalDateTimeDeserializer;
import com.anigenero.microservice.resource.json.deserializer.LocalTimeDeserializer;
import com.anigenero.microservice.resource.json.serializer.LocalDateSerializer;
import com.anigenero.microservice.resource.json.serializer.LocalDateTimeSerializer;
import com.anigenero.microservice.resource.json.serializer.LocalTimeSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.TimeZone;

@Provider
public class ObjectMapperResolverProvider implements ContextResolver<ObjectMapper> {

    private final ObjectMapper objectMapper;

    public ObjectMapperResolverProvider() {

        objectMapper = new ObjectMapper();

        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        objectMapper.registerModule(createModule());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));

        this.objectMapper.setDateFormat(dateFormat);

    }

    /**
     * Creates the mapper's module
     *
     * @return {@link SimpleModule}
     */
    private SimpleModule createModule() {

        SimpleModule module = new SimpleModule();

        attachDeserializers(module);
        attachSerializers(module);

        return module;

    }

    /**
     * Attaches deserializers to the module
     *
     * @param module {@link SimpleModule}
     */
    private void attachDeserializers(SimpleModule module) {

        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());

    }

    /**
     * Attaches serializers to the module
     *
     * @param module {@link SimpleModule}
     */
    private void attachSerializers(SimpleModule module) {

        module.addSerializer(LocalDate.class, new LocalDateSerializer());
        module.addSerializer(LocalTime.class, new LocalTimeSerializer());
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());

    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return this.objectMapper;
    }

}