package com.hikari.kiwi.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.hikari.kiwi.common.dto.BaseIdRequest;
import com.hikari.kiwi.common.dto.BasePageResponse;
import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.modules.sys.dao.UserCommentDao;
import com.hikari.kiwi.modules.sys.dto.UserCommentDTO;
import com.hikari.kiwi.modules.sys.dto.request.UserCommentCreateReq;
import com.hikari.kiwi.modules.sys.dto.request.UserCommentLevelRequest;
import com.hikari.kiwi.modules.sys.dto.request.UserCommentUpdateReq;
import com.hikari.kiwi.modules.sys.entity.UserComment;
import com.hikari.kiwi.modules.sys.service.UserCommentService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserCommentServiceImpl implements UserCommentService {

    @Autowired
    private UserCommentDao userCommentDao;

    @Override
    public int addUserComment(UserCommentCreateReq req) {
        UserComment entity = new UserComment();
        BeanUtils.copyProperties(req, entity);
        entity.setCreator(req.getTokenUserId());
        return userCommentDao.insert(entity);
    }

    @Override
    public int updateUserComment(UserCommentUpdateReq req) {
        UserComment entity = userCommentDao.selectById(req.getId());
        if (entity == null) {
            return this.addUserComment(req);
        }
        BeanUtils.copyProperties(req, entity);
        entity.setUpdater(req.getTokenUserId());
        return userCommentDao.updateById(entity);
    }

    @Override
    public int deleteUserComment(BaseIdRequest req) {
        return userCommentDao.deleteById(req.getId());
    }

    @Override
    public BaseResponse<UserCommentDTO> getUserCommentById(BaseIdRequest req) {
        UserComment entity = userCommentDao.selectById(req.getId());
        UserCommentDTO dto = new UserCommentDTO();
        BeanUtils.copyProperties(entity, dto);
        return BaseResponse.buildSuccessResponse(req.getRequestId(), dto);
    }


    @Override
    public BaseResponse<BasePageResponse<UserCommentDTO>> getCommentsByLevel(UserCommentLevelRequest req) {
        LambdaQueryWrapper<UserComment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserComment::getParentId, req.getParentId());

        wrapper.orderByDesc(UserComment::getCreateDate);

        Page<UserComment> page = new Page<>(req.getPage(), req.getLimit());
        IPage<UserComment> aiAgentPage = userCommentDao.selectPage(page, wrapper);
        List<UserComment> aiAgents = aiAgentPage.getRecords();
        long totalCount = aiAgentPage.getTotal();

        List<UserCommentDTO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(aiAgents)) {
            aiAgents.forEach(ai -> {
                UserCommentDTO dto = new UserCommentDTO();
                BeanUtils.copyProperties(ai, dto);
                list.add(dto);
            });
        }
        return BasePageResponse.buildSuccessPageResponse(req.getRequestId(), totalCount, (int) totalCount / req.getLimit(),
                req.getLimit(), req.getPage(), list);
    }
}    