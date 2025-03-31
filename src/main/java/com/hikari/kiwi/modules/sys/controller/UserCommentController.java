package com.hikari.kiwi.modules.sys.controller;

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
import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.modules.sys.dto.UserCommentDTO;
import com.hikari.kiwi.modules.sys.dto.request.UserCommentCreateReq;
import com.hikari.kiwi.modules.sys.dto.request.UserCommentLevelRequest;
import com.hikari.kiwi.modules.sys.dto.request.UserCommentUpdateReq;
import com.hikari.kiwi.modules.sys.entity.UserComment;
import com.hikari.kiwi.modules.sys.service.UserCommentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/userComment")
@Tag(name = "用户评论管理")
public class UserCommentController {

    @Autowired
    private UserCommentService userCommentService;

    @PostMapping("/add")
    @Operation(summary = "新增用户评论")
    public BaseResponse<Void> addUserComment(@RequestBody UserCommentCreateReq req) {
        log.info("addUserComment: {}", req);
        userCommentService.addUserComment(req);
        return BaseResponse.buildSuccessResponse(req.getRequestId());
    }

    @PostMapping("/update")
    @Operation(summary = "修改用户评论")
    public BaseResponse<UserCommentDTO> updateUserComment(@RequestBody UserCommentUpdateReq req) {
        log.info("updateUserComment: {}", req);
        userCommentService.updateUserComment(req);
        return BaseResponse.buildSuccessResponse(req.getRequestId());
    }

    @PostMapping("/delete")
    @Operation(summary = "删除用户评论")
    public BaseResponse<Boolean> deleteUserComment(@RequestBody BaseIdRequest req) {
        log.info("deleteUserComment: {}", req);
        userCommentService.deleteUserComment(req);
        return BaseResponse.buildSuccessResponse(req.getRequestId());
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询用户评论")
    public BaseResponse<BasePageResponse<UserCommentDTO>> pageUserComment(@RequestBody UserCommentLevelRequest req) {
        log.info("pageUserComment: {}", req);
        return userCommentService.getCommentsByLevel(req);
    }

}    