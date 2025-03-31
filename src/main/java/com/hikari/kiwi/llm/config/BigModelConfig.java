package com.hikari.kiwi.llm.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "bigmodel")
public class BigModelConfig {

    private ChatGLMLLM chatGLMLLM;


    @Data
    public static class ChatGLMLLM {
        private String modelName;
        private String apiKey;
    }

}
