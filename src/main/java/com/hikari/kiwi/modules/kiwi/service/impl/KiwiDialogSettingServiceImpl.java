package com.hikari.kiwi.modules.kiwi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hikari.kiwi.modules.agent.dao.AiAgentTemplateDao;
import com.hikari.kiwi.modules.agent.entity.AiAgentTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.modules.agent.dao.AiAgentDao;
import com.hikari.kiwi.modules.agent.entity.AiAgentEntity;
import com.hikari.kiwi.modules.kiwi.dto.KiwiDialogSetting;
import com.hikari.kiwi.modules.kiwi.dto.KiwiDialogSettingFindReq;
import com.hikari.kiwi.modules.kiwi.dto.KiwiDialogSettingReq;
import com.hikari.kiwi.modules.kiwi.service.KiwiDialogSettingService;

import java.util.Date;


@Slf4j
@Service
public class KiwiDialogSettingServiceImpl implements KiwiDialogSettingService {


    @Autowired
    private AiAgentDao aiAgentDao;

    @Autowired
    private AiAgentTemplateDao aiAgentTemplateDao;

    @Override
    public BaseResponse<KiwiDialogSetting> findDialog(KiwiDialogSettingFindReq req) {
        LambdaQueryWrapper<AiAgentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AiAgentEntity::getUserId, req.getTokenUserId());
        wrapper.eq(AiAgentEntity::getAgentCode, req.getAgentCode());
        AiAgentEntity entity = aiAgentDao.selectOne(wrapper);


        KiwiDialogSetting dto = new KiwiDialogSetting();
        if (null == entity) {
            LambdaQueryWrapper<AiAgentTemplate> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(AiAgentTemplate::getAgentCode, req.getAgentCode());
            AiAgentTemplate aiAgentTemplate = aiAgentTemplateDao.selectOne(wrapper1);
            if (null == aiAgentTemplate) {
                return null;
            }
            dto.setId(System.currentTimeMillis());
            BeanUtils.copyProperties(aiAgentTemplate, dto);
        } else {
            BeanUtils.copyProperties(entity, dto);
        }

        return BaseResponse.buildSuccessResponse(req.getRequestId(), dto);
    }

    @Transactional
    @Override
    public BaseResponse<KiwiDialogSetting> saveDialog(KiwiDialogSettingReq req) {
        AiAgentEntity entity = aiAgentDao.selectById(req.getId());

        if (null == entity) {
            entity = new AiAgentEntity();
            BeanUtils.copyProperties(req, entity);
            entity.setCreator(req.getTokenUserId());
            entity.setCreateDate(new Date());
            aiAgentDao.insert(entity);
        } else {
            BeanUtils.copyProperties(req, entity);
            entity.setUpdatedDate(new Date());
            entity.setUpdater(req.getTokenUserId());
            aiAgentDao.updateById(entity);
        }

        KiwiDialogSetting dto = new KiwiDialogSetting();
        BeanUtils.copyProperties(entity, dto);

        return BaseResponse.buildSuccessResponse(req.getRequestId(), dto);
    }
}
