package com.hikari.kiwi.modules.sys.dto.request;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import com.hikari.kiwi.common.dto.BaseRequest;

import java.util.Date;

@Data
@ToString(callSuper = true)
public class UserCommentCreateReq extends BaseRequest {

    @Schema(description = "回复的评论 ID")
    private Long commentId;
    @Schema(description = "智能体编码")
    private String agentCode;
    @Schema(description = "父级评论的id")
    private Long parentId;
    @Schema(description = "评论内容")
    private String content;
}
