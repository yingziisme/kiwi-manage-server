package com.hikari.kiwi.modules.sys.service;

import org.springframework.web.bind.annotation.RequestBody;
import com.hikari.kiwi.common.dto.BaseIdRequest;
import com.hikari.kiwi.common.dto.BasePageResponse;
import com.hikari.kiwi.common.dto.BaseRequest;
import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.modules.sys.dto.UserFocusDTO;
import com.hikari.kiwi.modules.sys.dto.request.UserFocusCreateReq;
import com.hikari.kiwi.modules.sys.dto.request.UserFocusPageRequest;
import com.hikari.kiwi.modules.sys.dto.request.UserFocusUpdateReq;

public interface UserFocusService {
    int addUserFocus(UserFocusCreateReq userFocus);

    int updateUserFocus(UserFocusUpdateReq userFocus);

    int deleteUserFocus(BaseIdRequest request);

    BaseResponse<BasePageResponse<UserFocusDTO>> pageUserFocus(UserFocusPageRequest request);

    BaseResponse<UserFocusDTO> getUserFocusById(BaseIdRequest request);

    BaseResponse<Long> count(BaseRequest req);
}    