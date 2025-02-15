package com.rollingstone.springaifunctions.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class QuestionRouter {
    private static final Logger logger = LoggerFactory.getLogger(QuestionRouter.class);
    private final Map<String, String> questionMethodMap = new HashMap<>();

    @PostConstruct
    public void loadConfiguration() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            questionMethodMap.putAll(objectMapper.readValue(
                    new ClassPathResource("questionRouting.json").getInputStream(),
                    HashMap.class)
            );
            logger.info("Loaded Question Routing Configuration: {}", questionMethodMap);
        } catch (IOException e) {
            logger.error("Failed to load question routing config", e);
        }
    }

    public String getMethodForQuestion(String question) {
        for (Map.Entry<String, String> entry : questionMethodMap.entrySet()) {
            if (question.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}

