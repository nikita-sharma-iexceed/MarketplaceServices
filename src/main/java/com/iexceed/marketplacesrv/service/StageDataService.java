package com.iexceed.marketplacesrv.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service
public class StageDataService {

	private Map<String, JsonNode> stageDataMap;

    @PostConstruct
    public void loadStageData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Read the JSON file from resources
        InputStream inputStream = getClass().getResourceAsStream("/static-stages.json");

        // Convert JSON file to a Map<String, JsonNode>
        stageDataMap = objectMapper.readValue(inputStream, new TypeReference<Map<String, JsonNode>>() {});
    }

    // Method to get the stage data by ID
    public JsonNode getStageDataById(String stageId) {
        return stageDataMap.get(stageId);
    }
}
