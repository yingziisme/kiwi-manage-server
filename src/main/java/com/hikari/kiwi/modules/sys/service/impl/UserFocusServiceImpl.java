package com.hikari.kiwi.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hikari.kiwi.modules.agent.entity.AiAgentEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hikari.kiwi.common.dto.BaseIdRequest;
import com.hikari.kiwi.common.dto.BasePageResponse;
import com.hikari.kiwi.common.dto.BaseRequest;
import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.modules.sys.dao.UserFocusDao;
import com.hikari.kiwi.modules.sys.dto.UserFocusDTO;
import com.hikari.kiwi.modules.sys.dto.request.UserFocusCreateReq;
import com.hikari.kiwi.modules.sys.dto.request.UserFocusPageRequest;
import com.hikari.kiwi.modules.sys.dto.request.UserFocusUpdateReq;
import com.hikari.kiwi.modules.sys.entity.UserFocus;
import com.hikari.kiwi.modules.sys.service.UserFocusService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserFocusServiceImpl implements UserFocusService {

    @Autowired
    private UserFocusDao userFocusDao;


    @Override
    public int addUserFocus(UserFocusCreateReq req) {
        UserFocus entity = new UserFocus();
        BeanUtils.copyProperties(req, entity);
        entity.setCreator(req.getTokenUserId());
        return userFocusDao.insert(entity);
    }

    @Override
    public int updateUserFocus(UserFocusUpdateReq req) {
        UserFocus entity = userFocusDao.selectById(req.getId());
        if (entity == null) {
            return this.addUserFocus(req);
        }
        BeanUtils.copyProperties(req, entity);
        entity.setUpdater(req.getTokenUserId());
        return userFocusDao.updateById(entity);
    }

    @Override
    public int deleteUserFocus(BaseIdRequest req) {
        return userFocusDao.deleteById(req.getId());
    }

    @Override
    public BaseResponse<UserFocusDTO> getUserFocusById(BaseIdRequest req) {
        UserFocus entity = userFocusDao.selectById(req.getId());
        UserFocusDTO dto = new UserFocusDTO();
        BeanUtils.copyProperties(entity, dto);
        return BaseResponse.buildSuccessResponse(req.getRequestId(), dto);
    }

    @Override
    public BaseResponse<Long> count(BaseRequest req) {
        LambdaQueryWrapper<UserFocus> wrapper = new LambdaQueryWrapper<>();
        if (req.getTokenUserId() != null) {
            wrapper.eq(UserFocus::getUserId, req.getTokenUserId());
        }

       return BaseResponse.buildSuccessResponse(req.getRequestId(), userFocusDao.selectCount(wrapper));
    }

    @Override
    public BaseResponse<BasePageResponse<UserFocusDTO>> pageUserFocus(UserFocusPageRequest req) {
        LambdaQueryWrapper<UserFocus> wrapper = new LambdaQueryWrapper<>();
        if (req.getTokenUserId() != null) {
            wrapper.eq(UserFocus::getUserId, req.getTokenUserId());
        }
        wrapper.orderByDesc(UserFocus::getCreateDate);
        Page<UserFocus> page = new Page<>(req.getPage(), req.getLimit());
        IPage<UserFocus> userFocusPage = userFocusDao.selectPage(page, wrapper);
        List<UserFocus> userFocuses = userFocusPage.getRecords();
        long totalCount = userFocusPage.getTotal();

        List<UserFocusDTO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userFocuses)) {
            userFocuses.forEach(focus -> {
                UserFocusDTO dto = new UserFocusDTO();
                BeanUtils.copyProperties(focus, dto);
                list.add(dto);
            });
        }
        return BasePageResponse.buildSuccessPageResponse(req.getRequestId(), totalCount, (int) (totalCount / req.getLimit()),
                req.getLimit(), req.getPage(), list);
    }
}    