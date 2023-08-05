package com.qa.orgchart.stepDefinitions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class jsonToHash {

    public static List<HashMap<String, String>> getHashList2() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File("src/test/java/com/qa/orgchart/jsonData/updatedJsonPayload.json"));


            List<HashMap<String, String>> hashMapList = new ArrayList<>();
            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    node.fields().forEachRemaining(entry -> {
                        String fieldName = entry.getKey();
                        String fieldValue = entry.getValue().asText();
                        hashMap.put(fieldName, fieldValue);
                    });
                    hashMapList.add(hashMap);
                }
            }

            return hashMapList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}