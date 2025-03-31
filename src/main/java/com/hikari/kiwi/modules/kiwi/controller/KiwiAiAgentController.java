package com.hikari.kiwi.modules.kiwi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hikari.kiwi.common.dto.BaseIdRequest;
import com.hikari.kiwi.common.dto.BasePageResponse;
import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.modules.agent.dto.*;
import com.hikari.kiwi.modules.agent.service.AiAgentService;
import com.hikari.kiwi.modules.agent.service.AiAgentTemplateService;
import com.hikari.kiwi.modules.kiwi.dto.KiwiAiAgentMessageReq;
import com.hikari.kiwi.modules.kiwi.dto.KiwiAiAgentPageReq;
import com.hikari.kiwi.modules.kiwi.service.KiwiAiAgentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/kiwi/agent")
@Tag(name = "智能体管理", description = "智能体相关的增删改查操作")
public class KiwiAiAgentController {

    @Autowired
    private KiwiAiAgentService kiwiAiAgentService;

    /**
     * 分页查询智能体
     *
     * @param req 偏移量
     * @return 智能体列表
     */
    @PostMapping("/list")
    @Operation(summary = "分页查询智能体", description = "获取默认智能体列表，如果有历史聊过天，则返回用户的智能体")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = List.class))),
    })
    public BaseResponse<BasePageResponse<AiAgentDTO>> getAiAgentsByPage(@RequestBody KiwiAiAgentPageReq req) {

        log.info("getAiAgentsByPage req: {}", req);
        return kiwiAiAgentService.getAiAgentsByPage(req);
    }

    @Operation(summary = "获取当前页面智能体的消息", description = "获取当前页面智能体的消息")
    @PostMapping("/message")
    public BaseResponse<String> getCurrentMessage(@RequestBody KiwiAiAgentMessageReq req) {
        log.info("getCurrentMessage req: {}", req);
       return kiwiAiAgentService.getCurrentMessage(req);
    }
}
