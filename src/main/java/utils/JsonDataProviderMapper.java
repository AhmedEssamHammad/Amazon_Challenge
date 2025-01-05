package utils;

import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonDataProviderMapper {

    private static JsonNode jsonNode; // Root node of JSON file

    public JsonDataProviderMapper(String filePath) {
        try {
            // Load the JSON file
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(filePath); // Path to JSON file
            jsonNode = mapper.readTree(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load constants.json");
        }
    }

    public String getValueOf(String key1, String key2) {
        if (jsonNode == null) {
            throw new RuntimeException("JSON file not loaded.");
        }
        // Traverse the JSON and retrieve the value for the given key and environment
        JsonNode valueNode = jsonNode.path(key1).path(key2);
        if (valueNode.isMissingNode()) {
            throw new RuntimeException("Constant '" + key2 + "' not found");
        }
        return valueNode.asText();
    }
}
