package com.hikari.kiwi.modules.kiwi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hikari.kiwi.llm.ModelFactory;
import com.hikari.kiwi.llm.bigmodel.BigModelClient;
import com.hikari.kiwi.modules.agent.dao.AiAgentDao;
import com.hikari.kiwi.modules.agent.dao.AiAgentTemplateDao;
import com.hikari.kiwi.modules.agent.entity.AiAgentEntity;
import com.hikari.kiwi.modules.agent.entity.AiAgentTemplate;
import com.hikari.kiwi.modules.kiwi.dto.KiwiDialogMessageReq;
import com.hikari.kiwi.modules.kiwi.service.KiwiDialogService;
import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class KiwiDialogServiceImpl implements KiwiDialogService {


    @Autowired
    private ModelFactory modelFactory;

    @Autowired
    private AiAgentDao aiAgentDao;

    @Autowired
    private AiAgentTemplateDao aiAgentTemplateDao;

    @Override
    public Flowable<ServerSentEvent<String>> getResponse(KiwiDialogMessageReq dto) {
        log.info("test req: {}", dto);
        LambdaQueryWrapper<AiAgentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AiAgentEntity::getUserId, dto.getTokenUserId());
        wrapper.eq(AiAgentEntity::getAgentCode, dto.getAgentCode());
        AiAgentEntity entity = aiAgentDao.selectOne(wrapper);
        if (null == entity) {
            LambdaQueryWrapper<AiAgentTemplate> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(AiAgentTemplate::getAgentCode, dto.getAgentCode());
            AiAgentTemplate aiAgentTemplate = aiAgentTemplateDao.selectOne(wrapper1);
            if (null == aiAgentTemplate) {
                return null;
            }
            entity = new AiAgentEntity();
            BeanUtils.copyProperties(aiAgentTemplate, entity);
            entity.setId(System.currentTimeMillis());
            entity.setCreateDate(new Date());
            entity.setCreator(dto.getTokenUserId());
            entity.setUserId(dto.getTokenUserId());
            aiAgentDao.insert(entity);
        }

        BigModelClient client = (BigModelClient) modelFactory.getModelClient(entity.getLlmModelId());

        return client.getResponse(String.valueOf(dto.getTokenUserId()), dto.getRequestId(), dto.getMessage(), entity.getSystemPrompt());
    }
}
