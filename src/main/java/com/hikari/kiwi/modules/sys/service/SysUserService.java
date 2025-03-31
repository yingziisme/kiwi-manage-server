package com.hikari.kiwi.modules.sys.service;

import com.hikari.kiwi.common.service.BaseService;
import com.hikari.kiwi.modules.sys.dto.PasswordDTO;
import com.hikari.kiwi.modules.sys.dto.SysUserDTO;
import com.hikari.kiwi.modules.sys.entity.SysUserEntity;


/**
 * 系统用户
 */
public interface SysUserService extends BaseService<SysUserEntity> {

    SysUserDTO getByUsername(String username);

    SysUserDTO getByUserId(Long userId);

    void save(SysUserDTO dto);

    void delete(Long[] ids);

    void changePassword(Long userId, PasswordDTO passwordDTO);
}
