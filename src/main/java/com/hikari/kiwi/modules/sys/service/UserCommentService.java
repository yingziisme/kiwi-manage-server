package com.hikari.kiwi.modules.sys.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hikari.kiwi.common.dto.BaseIdRequest;
import com.hikari.kiwi.common.dto.BasePageResponse;
import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.modules.sys.dto.UserCommentDTO;
import com.hikari.kiwi.modules.sys.dto.request.UserCommentCreateReq;
import com.hikari.kiwi.modules.sys.dto.request.UserCommentLevelRequest;
import com.hikari.kiwi.modules.sys.dto.request.UserCommentUpdateReq;

import java.util.List;

public interface UserCommentService {
    int addUserComment(UserCommentCreateReq userComment);

    int updateUserComment(UserCommentUpdateReq userComment);

    int deleteUserComment(BaseIdRequest request);

    BaseResponse<UserCommentDTO> getUserCommentById(BaseIdRequest request);

    BaseResponse<BasePageResponse<UserCommentDTO>> getCommentsByLevel(UserCommentLevelRequest req);
}    