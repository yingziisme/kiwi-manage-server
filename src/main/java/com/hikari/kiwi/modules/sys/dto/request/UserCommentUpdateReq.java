package com.hikari.kiwi.modules.sys.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class UserCommentUpdateReq extends UserCommentCreateReq {

    @Schema(description = "ID")
    private Long id;

}
