package com.hikari.kiwi.modules.device.service;

import com.hikari.kiwi.common.page.PageData;
import com.hikari.kiwi.modules.device.dto.DeviceHeaderDTO;
import com.hikari.kiwi.modules.device.entity.DeviceEntity;

import java.util.List;
import java.util.Map;

public interface DeviceService {
    DeviceEntity bindDevice(Long userId, DeviceHeaderDTO deviceHeader);
    
    List<DeviceEntity> getUserDevices(Long userId);
    
    void unbindDevice(Long userId, Long deviceId);
    
    PageData<DeviceEntity> adminDeviceList(Map<String, Object> params);
}