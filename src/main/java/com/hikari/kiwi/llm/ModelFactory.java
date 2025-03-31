package com.hikari.kiwi.llm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hikari.kiwi.llm.bigmodel.BigModelClient;

@Slf4j
@Service
public class ModelFactory {

    @Autowired
    private BigModelClient bigModelClient;

    public BaseModelClient getModelClient(String modelName) {

        switch (modelName) {
            case Model.LLM.ChatGLMLLM:
                return this.bigModelClient;
            default:
                throw new IllegalArgumentException("不支持的模型名称: " + modelName);
        }
    }
}
