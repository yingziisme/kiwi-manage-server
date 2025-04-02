package com.hikari.kiwi.llm.bigmodel;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.service.v4.model.*;
import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import com.hikari.kiwi.llm.BaseModelClient;
import com.hikari.kiwi.llm.config.BigModelConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.zhipu.oapi.service.v4.deserialize.MessageDeserializeFactory.defaultObjectMapper;

@Slf4j
@Service
public class BigModelClient implements BaseModelClient {

    private final ClientV4 client;
    private final ObjectMapper mapper = defaultObjectMapper();


    BigModelClient(BigModelConfig bigModelConfig) {
        client = new ClientV4.Builder(bigModelConfig.getChatGLMLLM().getApiKey()).build();
    }

    @Override
    public Flowable<ServerSentEvent<String>> getResponse(String userId, String requestId, String msg, String prompt) {
        ChatCompletionRequest chatCompletionRequest = buildChatCompletionRequest(requestId, msg, prompt);
        ModelApiResponse sseModelApiResp = client.invokeModelApi(chatCompletionRequest);
        if (sseModelApiResp.isSuccess()) {
            AtomicBoolean isFirst = new AtomicBoolean(true);
            StringBuilder message = new StringBuilder();
            return mapStreamToAccumulator(sseModelApiResp.getFlowable())
                    .map(accumulator -> {
                        if (isFirst.getAndSet(false)) {
                            message.append("Response: ");
                        }
                        if (accumulator.getDelta() != null && accumulator.getDelta().getTool_calls() != null) {
                            try {
                                String jsonString = mapper.writeValueAsString(accumulator.getDelta().getTool_calls());
                                message.append("tool_calls: ").append(jsonString);
                            } catch (JsonProcessingException e) {
                                log.error("Error serializing tool_calls", e);
                            }
                        }
                        if (accumulator.getDelta() != null && accumulator.getDelta().getContent() != null) {
                            message.append(accumulator.getDelta().getContent());
                        }
                        return ServerSentEvent.<String>builder()
                                .data(message.toString())
                                .build();
                    });
        }
        return Flowable.empty();
    }

    private ChatCompletionRequest buildChatCompletionRequest(String requestId, String msg, String prompt) {
        List<ChatMessage> messages = new ArrayList<>();
        if (StringUtils.isNotBlank(prompt)) {
            ChatMessage promptMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), prompt);
            messages.add(promptMessage);
        }

        ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(), msg);
        messages.add(chatMessage);

//        List<ChatTool> chatToolList = new ArrayList<>();
//        ChatTool chatTool = new ChatTool();
//        chatTool.setType(ChatToolType.FUNCTION.value());
//
//        ChatFunctionParameters chatFunctionParameters = new ChatFunctionParameters();
//        chatFunctionParameters.setType("object");
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("location", new HashMap<String, Object>() {{
//            put("type", "string");
//            put("description", "城市，如：北京");
//        }});
//        properties.put("unit", new HashMap<String, Object>() {{
//            put("type", "string");
//            put("enum", new ArrayList<String>() {{
//                add("celsius");
//                add("fahrenheit");
//            }});
//        }});
//        chatFunctionParameters.setProperties(properties);
//
//        ChatFunction chatFunction = ChatFunction.builder()
//                .name("get_weather")
//                .description("Get the current weather of a location")
//                .parameters(chatFunctionParameters)
//                .build();
//        chatTool.setFunction(chatFunction);
//        chatToolList.add(chatTool);

        HashMap<String, Object> extraJson = new HashMap<>();

        // 随机性
        extraJson.put("temperature", 0.5);
        // 控制生成的响应的最大 token 数量
        extraJson.put("max_tokens", 2048);

        return ChatCompletionRequest.builder()
                .model(Constants.ModelChatGLM4)
                .stream(Boolean.TRUE)
                .messages(messages)
                .requestId(requestId)
//                .tools(chatToolList)
                .toolChoice("auto")
                .extraJson(extraJson)
                .build();
    }

    public static Flowable<ChatMessageAccumulator> mapStreamToAccumulator(Flowable<ModelData> flowable) {
        return flowable.map(chunk -> {
            return new ChatMessageAccumulator(chunk.getChoices().get(0).getDelta(), null, chunk.getChoices().get(0), chunk.getUsage(), chunk.getCreated(), chunk.getId());
        });
    }
}

