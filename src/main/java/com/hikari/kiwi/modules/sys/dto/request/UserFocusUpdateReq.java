package com.hikari.kiwi.modules.sys.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import com.hikari.kiwi.common.dto.BaseRequest;

@Data
@ToString(callSuper = true)
public class UserFocusUpdateReq extends UserFocusCreateReq {

    @Schema(description = "ID")
    private Long id;

}
