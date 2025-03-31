package com.hikari.kiwi.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.hikari.kiwi.modules.sys.entity.UserComment;

import java.util.List;

@Mapper
public interface UserCommentDao extends BaseMapper<UserComment> {
}    