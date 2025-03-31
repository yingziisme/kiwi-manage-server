package com.hikari.kiwi.modules.agent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.hikari.kiwi.common.dto.BaseIdRequest;
import com.hikari.kiwi.common.dto.BasePageResponse;
import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.common.page.PageData;
import com.hikari.kiwi.modules.agent.dao.AiAgentDao;
import com.hikari.kiwi.modules.agent.dto.AiAgentCreateReq;
import com.hikari.kiwi.modules.agent.dto.AiAgentDTO;
import com.hikari.kiwi.modules.agent.dto.AiAgentPageReq;
import com.hikari.kiwi.modules.agent.entity.AiAgentEntity;
import com.hikari.kiwi.modules.agent.service.AiAgentService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class AiAgentServiceImpl implements AiAgentService {
    @Autowired
    private AiAgentDao aiAgentDao;

    @Override
    public int addAiAgent(AiAgentCreateReq req) {

        AiAgentEntity entity = new AiAgentEntity();
        BeanUtils.copyProperties(req, entity);
        return aiAgentDao.insert(entity);
    }

    @Override
    public int updateAiAgent(AiAgentCreateReq req) {
        AiAgentEntity entity =  aiAgentDao.selectById(req.getId());

        if (null == entity) {
            return this.addAiAgent(req);
        }
        BeanUtils.copyProperties(req, entity);
        return aiAgentDao.updateById(entity);
    }

    @Override
    public int deleteAiAgentById(BaseIdRequest req) {
        return aiAgentDao.deleteById(req.getId());
    }

    @Override
    public BaseResponse<AiAgentDTO> getAiAgentById(BaseIdRequest req) {

        AiAgentEntity entity =  aiAgentDao.selectById(req.getId());
        AiAgentDTO dto = new AiAgentDTO();
        BeanUtils.copyProperties(entity, dto);
        return BaseResponse.buildSuccessResponse(req.getRequestId(), dto);
    }

    @Override
    public BaseResponse<BasePageResponse<AiAgentDTO>> getAiAgentsByPage(AiAgentPageReq req) {
        LambdaQueryWrapper<AiAgentEntity> wrapper = new LambdaQueryWrapper<>();
        if (req.getTokenUserId() != null) {
            wrapper.eq(AiAgentEntity::getUserId, req.getTokenUserId());
        }

        Page<AiAgentEntity> page = new Page<>(req.getPage(), req.getLimit());
        IPage<AiAgentEntity> aiAgentPage = aiAgentDao.selectPage(page, wrapper);
        List<AiAgentEntity> aiAgents = aiAgentPage.getRecords();
        long totalCount = aiAgentPage.getTotal();

        List<AiAgentDTO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(aiAgents)) {
            aiAgents.forEach(ai -> {
                AiAgentDTO dto = new AiAgentDTO();
                BeanUtils.copyProperties(ai, dto);
                list.add(dto);
            });
        }
        return BasePageResponse.buildSuccessPageResponse(req.getRequestId(), totalCount, (int) totalCount / req.getLimit(),
                req.getLimit(), req.getPage(), list);
    }
}
