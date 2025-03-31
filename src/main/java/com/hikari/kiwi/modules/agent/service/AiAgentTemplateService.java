package com.hikari.kiwi.modules.agent.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hikari.kiwi.common.dto.BasePageResponse;
import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.modules.agent.dto.AiAgentTemplateDTO;
import com.hikari.kiwi.modules.agent.dto.AiAgentTemplatePageRequest;
import com.hikari.kiwi.modules.agent.dto.AiAgentTemplateRequest;
import com.hikari.kiwi.modules.agent.entity.AiAgentTemplate;

public interface AiAgentTemplateService {

    boolean addAgentTemplate(AiAgentTemplateRequest dto);

    boolean updateAgentTemplate(AiAgentTemplateRequest dto);

    boolean deleteAgentTemplate(String id);

    BaseResponse<BasePageResponse<AiAgentTemplateDTO>> getAgentTemplatePage(AiAgentTemplatePageRequest req);

    AiAgentTemplateDTO getAgentTemplateById(String id);
}
