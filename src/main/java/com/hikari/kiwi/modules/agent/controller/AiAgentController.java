package com.hikari.kiwi.modules.agent.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hikari.kiwi.common.dto.BaseIdRequest;
import com.hikari.kiwi.common.dto.BasePageResponse;
import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.modules.agent.dto.*;
import com.hikari.kiwi.modules.agent.service.AiAgentService;
import com.hikari.kiwi.modules.agent.service.AiAgentTemplateService;

import java.util.List;

@RestController
@RequestMapping("/agent")
@Tag(name = "智能体管理", description = "智能体相关的增删改查操作")
public class AiAgentController {

    @Autowired
    private AiAgentService aiAgentService;

    @Autowired
    private AiAgentTemplateService aiAgentTemplateService;

    /**
     * 新增智能体
     *
     * @param req 智能体信息
     * @return 受影响的行数
     */
    @PostMapping("/add")
    @Operation(summary = "新增智能体", description = "根据传入的智能体信息新增一个智能体")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "新增成功",
                    content = @Content(schema = @Schema(implementation = Integer.class))),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public BaseResponse<Void> addAiAgent(@RequestBody @Parameter(description = "智能体信息", required = true) AiAgentCreateReq req) {
        aiAgentService.addAiAgent(req);

        return BaseResponse.buildSuccessResponse(req.getRequestId());
    }

    /**
     * 修改智能体
     *
     * @param req 智能体信息
     * @return 受影响的行数
     */
    @PutMapping("/update")
    @Operation(summary = "修改智能体", description = "根据传入的智能体信息修改已有的智能体")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = Integer.class))),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "404", description = "未找到对应的智能体"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public BaseResponse<Void> updateAiAgent(@RequestBody @Parameter(description = "智能体信息", required = true) AiAgentCreateReq req) {
        aiAgentService.updateAiAgent(req);
        return BaseResponse.buildSuccessResponse(req.getRequestId());
    }

    /**
     * 删除智能体
     *
     * @param req 智能体 ID
     * @return 受影响的行数
     */
    @PostMapping("/delete")
    @Operation(summary = "删除智能体", description = "根据智能体 ID 删除对应的智能体")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = Integer.class))),
            @ApiResponse(responseCode = "404", description = "未找到对应的智能体"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public BaseResponse<Void> deleteAiAgentById(@RequestBody BaseIdRequest req) {
        aiAgentService.deleteAiAgentById(req);
        return BaseResponse.buildSuccessResponse(req.getRequestId());
    }

    /**
     * 根据 ID 查询智能体
     *
     * @param req 智能体 ID
     * @return 智能体信息
     */
    @PostMapping("/get")
    @Operation(summary = "根据 ID 查询智能体", description = "根据智能体 ID 查询对应的智能体信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = AiAgentDTO.class))),
            @ApiResponse(responseCode = "404", description = "未找到对应的智能体"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public BaseResponse<AiAgentDTO> getAiAgentById(@RequestBody BaseIdRequest req) {
        return aiAgentService.getAiAgentById(req);
    }

    /**
     * 分页查询智能体
     *
     * @param req 偏移量
     * @return 智能体列表
     */
    @PostMapping("/list")
    @Operation(summary = "分页查询智能体", description = "根据偏移量和每页数量分页查询智能体列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public BaseResponse<BasePageResponse<AiAgentDTO>> getAiAgentsByPage(@RequestBody AiAgentPageReq req) {
        return aiAgentService.getAiAgentsByPage(req);
    }

    @Operation(summary = "新增智能体模板")
    @PostMapping("/template/add")
    public BaseResponse<Void> addAgentTemplate(@RequestBody AiAgentTemplateRequest dto) {
        boolean result = aiAgentTemplateService.addAgentTemplate(dto);
        if (result) {
            return BaseResponse.buildSuccessResponse(dto.getRequestId());
        } else {
            return BaseResponse.buildFailureResponse("新增失败", 500, dto.getRequestId());
        }
    }

    @Operation(summary = "修改智能体模板")
    @PostMapping("/update")
    public BaseResponse<Void> updateAgentTemplate(@RequestBody AiAgentTemplateRequest dto) {
        boolean result = aiAgentTemplateService.updateAgentTemplate(dto);
        if (result) {
            return BaseResponse.buildSuccessResponse(dto.getRequestId());
        } else {
            return BaseResponse.buildFailureResponse("修改失败", 500, dto.getRequestId());
        }
    }

    @Operation(summary = "删除智能体模板")
    @PostMapping("/template/delete")
    public BaseResponse<Void> deleteAgentTemplate(@RequestBody BaseIdRequest request) {
        boolean result = aiAgentTemplateService.deleteAgentTemplate(request.getId());
        if (result) {
            return BaseResponse.buildSuccessResponse(request.getRequestId());
        } else {
            return BaseResponse.buildFailureResponse("删除失败", 500, request.getRequestId());
        }
    }

    @Operation(summary = "分页查询智能体模板")
    @PostMapping("/template/page")
    public BaseResponse<BasePageResponse<AiAgentTemplateDTO>> getAgentTemplatePage(@RequestBody AiAgentTemplatePageRequest request) {
        return aiAgentTemplateService.getAgentTemplatePage(request);
    }

    @Operation(summary = "根据 ID 查询智能体模板")
    @PostMapping("/template/getById")
    public BaseResponse<AiAgentTemplateDTO> getAgentTemplateById(@RequestBody BaseIdRequest request) {
        AiAgentTemplateDTO dto = aiAgentTemplateService.getAgentTemplateById(request.getId());
        if (dto != null) {
            return BaseResponse.buildSuccessResponse(request.getRequestId(), dto);
        } else {
            return BaseResponse.buildFailureResponse("未找到记录", 404, request.getRequestId());
        }
    }
}
