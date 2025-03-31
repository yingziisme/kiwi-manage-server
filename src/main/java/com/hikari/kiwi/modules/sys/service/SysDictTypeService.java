package com.hikari.kiwi.modules.sys.service;

import com.hikari.kiwi.common.page.PageData;
import com.hikari.kiwi.common.service.BaseService;
import com.hikari.kiwi.modules.sys.dto.SysDictTypeDTO;
import com.hikari.kiwi.modules.sys.entity.DictType;
import com.hikari.kiwi.modules.sys.entity.SysDictTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 */
public interface SysDictTypeService extends BaseService<SysDictTypeEntity> {

    PageData<SysDictTypeDTO> page(Map<String, Object> params);

    SysDictTypeDTO get(Long id);

    void save(SysDictTypeDTO dto);

    void update(SysDictTypeDTO dto);

    void delete(Long[] ids);

    List<DictType> getAllList();

    List<DictType> getDictTypeList();
}