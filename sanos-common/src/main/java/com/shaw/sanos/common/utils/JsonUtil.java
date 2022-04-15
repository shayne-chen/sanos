package com.shaw.sanos.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.shaw.sanos.common.exceptions.SanosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.util.Collections;
import java.util.List;

/**
 * @author shaw
 * @date 2022/4/7
 */
public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static <S> String toJSONString(S s) {
        if (null == s) {
            return null;
        }
        try {
            return mapper.writeValueAsString(s);
        } catch (JsonProcessingException e) {
            log.error("转换出错", e);
            throw new SanosException("parse to json string error", e);
        }
    }

    public static <S> S parseObject(@NonNull String json, Class<S> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("转换出错", e);
            throw new SanosException("parse json error", e);
        }

    }

    public static <T> List<T> toList(@NonNull String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, getCollectionType(List.class, clazz));
        } catch (JsonProcessingException e) {
            String className = clazz.getSimpleName();
            log.error("parse json [{}] to class [{}] error：{}", json, className, e);
            throw new SanosException("parse to list error", e);
        }
    }

    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
