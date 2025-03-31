package com.hikari.kiwi.modules.agent.service;

import com.hikari.kiwi.common.dto.BaseIdRequest;
import com.hikari.kiwi.common.dto.BasePageResponse;
import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.modules.agent.dto.AiAgentCreateReq;
import com.hikari.kiwi.modules.agent.dto.AiAgentDTO;
import com.hikari.kiwi.modules.agent.dto.AiAgentPageReq;

public interface AiAgentService {

    // 新增智能体
    int addAiAgent(AiAgentCreateReq aiAgent);

    // 修改智能体
    int updateAiAgent(AiAgentCreateReq aiAgent);

    // 删除智能体
    int deleteAiAgentById(BaseIdRequest id);

    // 根据 ID 查询智能体
    BaseResponse<AiAgentDTO> getAiAgentById(BaseIdRequest id);

    // 分页查询智能体
    BaseResponse<BasePageResponse<AiAgentDTO>> getAiAgentsByPage(AiAgentPageReq req);
}
