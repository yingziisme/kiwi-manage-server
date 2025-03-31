package com.hikari.kiwi.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import com.hikari.kiwi.common.dao.BaseDao;
import com.hikari.kiwi.modules.sys.entity.SysUserEntity;

/**
 * 系统用户
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {

}