package com.hikari.kiwi.modules.agent.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.hikari.kiwi.common.dto.BasePageResponse;
import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.modules.agent.dao.AiAgentTemplateDao;
import com.hikari.kiwi.modules.agent.dto.AiAgentTemplateDTO;
import com.hikari.kiwi.modules.agent.dto.AiAgentTemplatePageRequest;
import com.hikari.kiwi.modules.agent.dto.AiAgentTemplateRequest;
import com.hikari.kiwi.modules.agent.entity.AiAgentTemplate;
import com.hikari.kiwi.modules.agent.service.AiAgentTemplateService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AiAgentTemplateServiceImpl implements AiAgentTemplateService {

    @Autowired
    private AiAgentTemplateDao aiAgentTemplateDao;

    @Override
    public boolean addAgentTemplate(AiAgentTemplateRequest dto) {
        AiAgentTemplate entity = convertToEntity(dto);
        entity.setCreateDate(new Date());
        return aiAgentTemplateDao.insert(entity) > 0;
    }

    @Override
    public boolean updateAgentTemplate(AiAgentTemplateRequest dto) {
        AiAgentTemplate entity = convertToEntity(dto);
        entity.setUpdatedDate(new Date());
        return aiAgentTemplateDao.updateById(entity) > 0;
    }

    @Override
    public boolean deleteAgentTemplate(String id) {
        return aiAgentTemplateDao.deleteById(id) > 0;
    }

    @Override
    public BaseResponse<BasePageResponse<AiAgentTemplateDTO>> getAgentTemplatePage(AiAgentTemplatePageRequest req) {

        LambdaQueryWrapper<AiAgentTemplate> wrapper = new LambdaQueryWrapper<>();

        Page<AiAgentTemplate> page = new Page<>(req.getPage(), req.getLimit());
        IPage<AiAgentTemplate> aiAgentPage = aiAgentTemplateDao.selectPage(page, wrapper);
        List<AiAgentTemplate> aiAgents = aiAgentPage.getRecords();
        long totalCount = aiAgentPage.getTotal();

        List<AiAgentTemplateDTO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(aiAgents)) {
            aiAgents.forEach(ai -> {
                AiAgentTemplateDTO dto = new AiAgentTemplateDTO();
                BeanUtils.copyProperties(ai, dto);
                list.add(dto);
            });
        }
        return BasePageResponse.buildSuccessPageResponse(req.getRequestId(), totalCount, (int) totalCount / req.getLimit(),
                req.getLimit(), req.getPage(), list);
    }

    @Override
    public AiAgentTemplateDTO getAgentTemplateById(String id) {
        AiAgentTemplate ai = aiAgentTemplateDao.selectById(id);
        AiAgentTemplateDTO dto = new AiAgentTemplateDTO();
        if (null != ai) {
            BeanUtils.copyProperties(ai, dto);
        }
        return dto;
    }

    private AiAgentTemplate convertToEntity(AiAgentTemplateDTO dto) {
        AiAgentTemplate entity = new AiAgentTemplate();
        entity.setId(dto.getId());
        entity.setAgentCode(dto.getAgentCode());
        entity.setAgentName(dto.getAgentName());
        entity.setAsrModelId(dto.getAsrModelId());
        entity.setVadModelId(dto.getVadModelId());
        entity.setLlmModelId(dto.getLlmModelId());
        entity.setTtsModelId(dto.getTtsModelId());
        entity.setTtsVoiceId(dto.getTtsVoiceId());
        entity.setMemModelId(dto.getMemModelId());
        entity.setIntentModelId(dto.getIntentModelId());
        entity.setSystemPrompt(dto.getSystemPrompt());
        entity.setLangCode(dto.getLangCode());
        entity.setLanguage(dto.getLanguage());
        entity.setSort(dto.getSort());
        entity.setCreator(dto.getCreator());
        entity.setUpdater(dto.getUpdater());
        return entity;
    }

    private AiAgentTemplate convertToEntity(AiAgentTemplateRequest dto) {
        AiAgentTemplate entity = new AiAgentTemplate();
        entity.setId(dto.getId());
        entity.setAgentCode(dto.getAgentCode());
        entity.setAgentName(dto.getAgentName());
        entity.setAsrModelId(dto.getAsrModelId());
        entity.setVadModelId(dto.getVadModelId());
        entity.setLlmModelId(dto.getLlmModelId());
        entity.setTtsModelId(dto.getTtsModelId());
        entity.setTtsVoiceId(dto.getTtsVoiceId());
        entity.setMemModelId(dto.getMemModelId());
        entity.setIntentModelId(dto.getIntentModelId());
        entity.setSystemPrompt(dto.getSystemPrompt());
        entity.setLangCode(dto.getLangCode());
        entity.setLanguage(dto.getLanguage());
        entity.setSort(dto.getSort());
        entity.setCreator(dto.getCreator());
        entity.setUpdater(dto.getUpdater());
        return entity;
    }
}
