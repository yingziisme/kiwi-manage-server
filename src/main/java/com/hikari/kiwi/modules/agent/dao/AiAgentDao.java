package com.hikari.kiwi.modules.agent.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.hikari.kiwi.modules.agent.entity.AiAgentEntity;

@Mapper
public interface AiAgentDao extends BaseMapper<AiAgentEntity> {
}
