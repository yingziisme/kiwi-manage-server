package com.hikari.kiwi.modules.sys.service;

import com.hikari.kiwi.common.page.PageData;
import com.hikari.kiwi.common.service.BaseService;
import com.hikari.kiwi.modules.sys.dto.SysDictDataDTO;
import com.hikari.kiwi.modules.sys.entity.SysDictDataEntity;

import java.util.Map;

/**
 * 数据字典
 */
public interface SysDictDataService extends BaseService<SysDictDataEntity> {

    PageData<SysDictDataDTO> page(Map<String, Object> params);

    SysDictDataDTO get(Long id);

    void save(SysDictDataDTO dto);

    void update(SysDictDataDTO dto);

    void delete(Long[] ids);

}