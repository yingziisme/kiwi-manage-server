package com.hikari.kiwi.modules.kiwi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.hikari.kiwi.common.dto.BasePageResponse;
import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.modules.agent.dao.AiAgentDao;
import com.hikari.kiwi.modules.agent.dao.AiAgentTemplateDao;
import com.hikari.kiwi.modules.agent.dto.AiAgentDTO;
import com.hikari.kiwi.modules.agent.entity.AiAgentEntity;
import com.hikari.kiwi.modules.agent.entity.AiAgentTemplate;
import com.hikari.kiwi.modules.kiwi.dto.KiwiAiAgentMessageReq;
import com.hikari.kiwi.modules.kiwi.dto.KiwiAiAgentPageReq;
import com.hikari.kiwi.modules.kiwi.service.KiwiAiAgentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class KiwiAiAgentServiceImpl implements KiwiAiAgentService {

    @Autowired
    private AiAgentDao aiAgentDao;

    @Autowired
    private AiAgentTemplateDao aiAgentTemplateDao;

    @Override
    public BaseResponse<BasePageResponse<AiAgentDTO>> getAiAgentsByPage(KiwiAiAgentPageReq req) {
        LambdaQueryWrapper<AiAgentTemplate> wrapper = new LambdaQueryWrapper<>();

        Page<AiAgentTemplate> page = new Page<>(req.getPage(), req.getLimit());
        IPage<AiAgentTemplate> aiAgentPage = aiAgentTemplateDao.selectPage(page, wrapper);
        List<AiAgentTemplate> aiAgents = aiAgentPage.getRecords();
        long totalCount = aiAgentPage.getTotal();

        Map<String, AiAgentDTO> map = new HashMap<>();
        List<AiAgentDTO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(aiAgents)) {
            aiAgents.forEach(ai -> {
                AiAgentDTO dto = new AiAgentDTO();
                BeanUtils.copyProperties(ai, dto);
                map.put(ai.getAgentCode(), dto);
                list.add(dto);
            });
        }
        if (!map.isEmpty()) {
            LambdaQueryWrapper<AiAgentEntity> wrapper1 = new LambdaQueryWrapper<>();
            if (req.getTokenUserId() != null) {
                wrapper1.eq(AiAgentEntity::getUserId, req.getTokenUserId());
            }
            wrapper1.in(AiAgentEntity::getAgentCode, map.keySet());

            List<AiAgentEntity> aiAgentEntities = aiAgentDao.selectList(wrapper1);

            if (!CollectionUtils.isEmpty(aiAgentEntities)) {
                aiAgentEntities.forEach(ai -> {
                    if (map.containsKey(ai.getAgentCode())) {
                        AiAgentDTO dto = map.get(ai.getAgentCode());
                        BeanUtils.copyProperties(ai, dto);
                    }
                });
            }
            return BasePageResponse.buildSuccessPageResponse(req.getRequestId(), totalCount, (int) totalCount / req.getLimit(),
                    req.getLimit(), req.getPage(), list);
        }


        return BasePageResponse.buildSuccessPageResponse(req.getRequestId(), totalCount, (int) totalCount / req.getLimit(),
                req.getLimit(), req.getPage(), list);
    }

    @Override
    public BaseResponse<String> getCurrentMessage(KiwiAiAgentMessageReq req) {
        return null;
    }
}
