package br.nikolastrapp.receba.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;

public class CustomRedisSerializer<T> implements RedisSerializer<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(T object) throws SerializationException {
        if (object == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new SerializationException("Error serializing object", e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null) {
            return null;
        }

        try {
            return objectMapper.readValue(bytes, getTargetClass());
        } catch (IOException e) {
            throw new SerializationException("Error deserializing object", e);
        }
    }

    private Class<T> getTargetClass() {
        return (Class<T>) objectMapper.getTypeFactory().constructType(Object.class).getRawClass();
    }
}
