package com.hikari.kiwi.modules.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.hikari.kiwi.common.dto.BaseRequest;

@Data
public class CaptchaDTO extends BaseRequest {

    @Schema(description = "uuid")
    private String uuid;
}
