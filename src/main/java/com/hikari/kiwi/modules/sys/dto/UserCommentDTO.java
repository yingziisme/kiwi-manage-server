package com.hikari.kiwi.modules.sys.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "用户评论数据传输对象")
public class UserCommentDTO {
    @Schema(description = "唯一标识")
    private Long id;
    @Schema(description = "所属用户 ID")
    private Long userId;
    @Schema(description = "智能体编码")
    private String agentCode;
    @Schema(description = "父级评论的id")
    private Long parentId;
    @Schema(description = "评论内容")
    private String content;
    @Schema(description = "创建者 ID")
    private Long creator;
    @Schema(description = "创建时间")
    private Date createDate;
    @Schema(description = "更新者 ID")
    private Long updater;
    @Schema(description = "更新时间")
    private Date updatedDate;
}    