package com.hikari.kiwi.modules.kiwi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hikari.kiwi.common.dto.BaseIdRequest;
import com.hikari.kiwi.common.dto.BasePageResponse;
import com.hikari.kiwi.common.dto.BaseRequest;
import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.modules.sys.dto.UserFocusDTO;
import com.hikari.kiwi.modules.sys.dto.request.UserFocusCreateReq;
import com.hikari.kiwi.modules.sys.dto.request.UserFocusPageRequest;
import com.hikari.kiwi.modules.sys.dto.request.UserFocusUpdateReq;
import com.hikari.kiwi.modules.sys.service.UserFocusService;

@Slf4j
@RestController
@RequestMapping("/kiwi/user/focus")
@Tag(name = "用户关注管理")
public class KiwiUserFocusController {

    @Autowired
    private UserFocusService userFocusService;

    @PostMapping("/add")
    @Operation(summary = "新增用户关注")
    public BaseResponse<UserFocusDTO> addUserFocus(@RequestBody UserFocusCreateReq req) {
        log.info("addUserFocus: {}", req);
        userFocusService.addUserFocus(req);
        return BaseResponse.buildSuccessResponse(req.getRequestId());
    }

    @PostMapping("/delete")
    @Operation(summary = "删除用户关注")
    public BaseResponse<Boolean> deleteUserFocus(@RequestBody BaseIdRequest req) {
        log.info("deleteUserFocus: {}", req);
        userFocusService.deleteUserFocus(req);
        return BaseResponse.buildSuccessResponse(req.getRequestId());
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询用户关注")
    public BaseResponse<BasePageResponse<UserFocusDTO>> pageUserFocus(@RequestBody UserFocusPageRequest req) {
        log.info("pageUserFocus: {}", req);
        return userFocusService.pageUserFocus(req);
    }

    @PostMapping("/count")
    @Operation(summary = "关注智能体数量")
    public BaseResponse<Long> count(@RequestBody BaseRequest req) {
        log.info("count: {}", req);
        return userFocusService.count(req);
    }

}    