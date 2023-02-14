package com.assignment.bhc.utilities;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MockReadUtil {

    public static List readListFromJsonFile(String jsonFilePath, Class clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return objectMapper.readValue(readObjectFromJsonFile(jsonFilePath),
                type);
    }

    public static Map readMapFromJsonFile(String jsonFilePath, Class keyType, Class valueType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JavaType type = objectMapper.getTypeFactory().constructMapType(Map.class, keyType, valueType);
        return objectMapper.readValue(readObjectFromJsonFile(jsonFilePath),
                type);
    }

    public static Object readObjectFromJsonFile(String jsonFilePath, Class clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper.readValue(readObjectFromJsonFile(jsonFilePath), clazz);
    }

    public static String readObjectFromJsonFile(String jsonFile) throws IOException {
        File file = ResourceUtils.getFile(jsonFile);
        String json = null;
        try (BufferedReader br = Files.newBufferedReader(file.toPath())) {
            json = br.lines().collect(Collectors.joining("\n"));
        }
        return json;
    }

}

