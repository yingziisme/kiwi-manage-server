package com.hikari.kiwi.modules.kiwi.dto;

import lombok.Data;
import lombok.ToString;
import com.hikari.kiwi.common.dto.BaseRequest;

@Data
@ToString(callSuper = true)
public class KiwiDialogTestDTO extends BaseRequest {

    private String mode;
    private String msg;
}
