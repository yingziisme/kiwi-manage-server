package com.hikari.kiwi.modules.security.service.impl;

import com.hikari.kiwi.common.user.UserDetail;
import com.hikari.kiwi.modules.security.dao.SysUserTokenDao;
import com.hikari.kiwi.modules.security.entity.SysUserTokenEntity;
import com.hikari.kiwi.modules.security.service.ShiroService;
import com.hikari.kiwi.modules.sys.dao.SysUserDao;
import com.hikari.kiwi.modules.sys.entity.SysUserEntity;
import com.hikari.kiwi.modules.sys.enums.SuperAdminEnum;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class ShiroServiceImpl implements ShiroService {
    private final SysUserDao sysUserDao;
    private final SysUserTokenDao sysUserTokenDao;

    @Override
    public Set<String> getUserPermissions(UserDetail user) {
        //系统管理员，拥有最高权限
        // TODO: 暂时写死，后续改成从数据库查询
        List<String> permissionsList = new ArrayList<>();
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String permissions : permissionsList) {
            if (StringUtils.isBlank(permissions)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(permissions.trim().split(",")));
        }

        return permsSet;
    }

    @Override
    public SysUserTokenEntity getByToken(String token) {
        return sysUserTokenDao.getByToken(token);
    }

    @Override
    public SysUserEntity getUser(Long userId) {
        return sysUserDao.selectById(userId);
    }
}