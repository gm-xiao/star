package com.sofyun.star.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class ObjectSerializer implements RedisSerializer<Object> {
    private Converter<Object, byte[]> serializingConverter = new SerializingConverter();
    private Converter<byte[], Object> deserializingConverter = new DeserializingConverter();
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    @Override
    public byte[] serialize(Object obj) throws SerializationException {
        if (obj == null) {
            return EMPTY_BYTE_ARRAY ;
        }
        return this.serializingConverter.convert(obj);
    }
    @Override
    public Object deserialize(byte[] data) throws SerializationException {
        if (data == null || data.length == 0) {
            return null ;
        }
        return this.deserializingConverter.convert(data);
    }

}