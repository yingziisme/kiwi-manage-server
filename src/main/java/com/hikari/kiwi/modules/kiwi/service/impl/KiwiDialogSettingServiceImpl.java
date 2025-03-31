package com.hikari.kiwi.modules.kiwi.service.impl;

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

    @Override
    public BaseResponse<KiwiDialogSetting> findDialog(KiwiDialogSettingFindReq req) {
        AiAgentEntity entity = aiAgentDao.selectById(req.getAgentId());
        KiwiDialogSetting dto = new KiwiDialogSetting();
        BeanUtils.copyProperties(entity, dto);
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
            aiAgentDao.updateById(entity);
        }

        KiwiDialogSetting dto = new KiwiDialogSetting();
        BeanUtils.copyProperties(entity, dto);

        return BaseResponse.buildSuccessResponse(req.getRequestId(), dto);
    }
}
