package com.hikari.kiwi.modules.kiwi.controller;

import io.reactivex.Flowable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.common.page.TokenDTO;
import com.hikari.kiwi.llm.ModelFactory;
import com.hikari.kiwi.llm.bigmodel.BigModelClient;
import com.hikari.kiwi.modules.kiwi.dto.KiwiDialogTestDTO;
import com.hikari.kiwi.modules.security.dto.LoginDTO;

@Slf4j
@RestController
@RequestMapping("/kiwi/dialog")
@Tag(name = "对话相关接口", description = "对话相关接口")
public class KiwiDialogController {

    @Autowired
    private ModelFactory modelFactory;


    @PostMapping("/display")
    @Operation(summary = "语音播放")
    public BaseResponse<TokenDTO> display(@RequestBody LoginDTO login) {
        return null;
    }

    @PostMapping("/send")
    @Operation(summary = "发送信息")
    public Flowable<ServerSentEvent<String>> send(@RequestBody KiwiDialogTestDTO dto) {

        log.info("test req: {}", dto);
        BigModelClient client = (BigModelClient) modelFactory.getModelClient(dto.getMode());

        return client.getResponse(String.valueOf(dto.getTokenUserId()), dto.getRequestId(), dto.getMsg());

    }
    @PostMapping(value = "/test", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Operation(summary = "获取文字响应（逐字响应")
    public Flowable<ServerSentEvent<String>> test(@RequestBody KiwiDialogTestDTO dto) {

        log.info("test req: {}", dto);
        BigModelClient client = (BigModelClient) modelFactory.getModelClient(dto.getMode());

        return client.getResponse(String.valueOf(dto.getTokenUserId()), dto.getRequestId(), dto.getMsg());
    }


}
