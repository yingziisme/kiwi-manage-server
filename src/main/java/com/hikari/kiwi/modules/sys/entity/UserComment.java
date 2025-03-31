package com.hikari.kiwi.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.hikari.kiwi.common.entity.BaseEntity;

import java.util.Date;

@Data
@TableName("user_comment_tb")
@Schema(description = "智能体的评论信息")
public class UserComment extends BaseEntity {

    @Schema(description = "所属用户 ID")
    private Long userId;
    @Schema(description = "智能体编码")
    private String agentCode;
    @Schema(description = "父级评论的id")
    private Long parentId;
    @Schema(description = "评论内容")
    private String content;
}    