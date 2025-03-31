package com.hikari.kiwi.modules.sys.dao;

import com.hikari.kiwi.common.dao.BaseDao;
import com.hikari.kiwi.modules.sys.dto.SysDictDataDTO;
import com.hikari.kiwi.modules.sys.entity.DictData;
import com.hikari.kiwi.modules.sys.entity.SysDictDataEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典数据
 */
@Mapper
public interface SysDictDataDao extends BaseDao<SysDictDataEntity> {

    /**
     * 字典数据列表
     */
    List<DictData> getDictDataList();

    List<SysDictDataDTO> getDataByTypeCode(String dictType);
}
