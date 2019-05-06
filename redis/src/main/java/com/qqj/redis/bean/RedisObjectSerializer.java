package com.qqj.redis.bean;/**
 * @auther qqjbest qqjbest
 * @create 2019-05-06
 */

import com.qqj.util.ValidatorUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * redis 序列化对象
 *
 * @auther qjqiu
 * @create 2019-05-06
 */
public class RedisObjectSerializer implements RedisSerializer<Object>{

    private Converter<Object, byte[]> serializer = new SerializingConverter();

    private Converter<byte[], Object> deserializer = new DeserializingConverter();

    static final byte[] EMPTY_ARRAY = new byte[0];

    public Object deserialize(byte[] bytes){
        if(ValidatorUtil.isNull(bytes)){
            return null;
        }
        try {
            return deserializer.convert(bytes);
        } catch (Exception ex){
            throw new SerializationException("序列化失败", ex);
        }
    }


    @Override
    public byte[] serialize(Object object) throws SerializationException {
        if (ValidatorUtil.isNull(object)) {
            return EMPTY_ARRAY;
        }
        try {
            return serializer.convert(object);
        } catch (Exception ex) {
            return EMPTY_ARRAY;
        }
    }
}
