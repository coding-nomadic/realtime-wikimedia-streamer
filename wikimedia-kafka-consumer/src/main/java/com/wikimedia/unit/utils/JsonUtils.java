package com.wikimedia.unit.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    JsonUtils() {
    }

    public static String toString(Object objectToConvert) throws IOException {
        return objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsString(objectToConvert);
    }

    public static <T> T convertWithClass(String jsonString, Class<T> type) throws IOException {
        return objectMapper.readValue(jsonString, type);
    }
}
