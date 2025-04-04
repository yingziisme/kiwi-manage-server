package com.hikari.kiwi.modules.device.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import com.hikari.kiwi.common.constant.Constant;
import com.hikari.kiwi.common.exception.ErrorCode;
import com.hikari.kiwi.common.page.PageData;
import com.hikari.kiwi.common.redis.RedisKeys;
import com.hikari.kiwi.common.redis.RedisUtils;
import com.hikari.kiwi.common.user.UserDetail;
import com.hikari.kiwi.common.utils.JsonUtils;
import com.hikari.kiwi.common.utils.Result;
import com.hikari.kiwi.modules.device.dto.DeviceHeaderDTO;
import com.hikari.kiwi.modules.device.dto.DeviceUnBindDTO;
import com.hikari.kiwi.modules.device.entity.DeviceEntity;
import com.hikari.kiwi.modules.device.service.DeviceService;
import com.hikari.kiwi.modules.security.user.SecurityUser;

import java.util.List;
import java.util.Map;

@Tag(name = "设备管理")
@AllArgsConstructor
@RestController
@RequestMapping("/device")
public class DeviceController {
    private final DeviceService deviceService;
    private final RedisUtils redisUtils;


    @PostMapping("/bind/{deviceCode}")
    @Operation(summary = "绑定设备")
    @RequiresPermissions("sys:role:normal")
    public Result<DeviceEntity> bindDevice(@PathVariable String deviceCode) {
        UserDetail user = SecurityUser.getUser();

        String deviceHeaders = (String) redisUtils.get(RedisKeys.getDeviceCaptchaKey(deviceCode));
        if (StringUtils.isBlank(deviceHeaders)) {
            return new Result<DeviceEntity>().error(ErrorCode.DEVICE_CAPTCHA_ERROR);
        }
        DeviceHeaderDTO deviceHeader = JsonUtils.parseObject(deviceHeaders.getBytes(), DeviceHeaderDTO.class);
        DeviceEntity device = deviceService.bindDevice(user.getId(), deviceHeader);
        return new Result<DeviceEntity>().ok(device);
    }

    @GetMapping("/bind")
    @Operation(summary = "获取已绑定设备")
    @RequiresPermissions("sys:role:normal")
    public Result<List<DeviceEntity>> getUserDevices() {
        UserDetail user = SecurityUser.getUser();
        List<DeviceEntity> devices = deviceService.getUserDevices(user.getId());
        return new Result<List<DeviceEntity>>().ok(devices);
    }

    @PostMapping("/unbind")
    @Operation(summary = "解绑设备")
    @RequiresPermissions("sys:role:normal")
    public Result unbindDevice(@RequestBody DeviceUnBindDTO unDeviveBind) {
        UserDetail user = SecurityUser.getUser();
        deviceService.unbindDevice(user.getId(), unDeviveBind.getDeviceId());
        return new Result();
    }

    @GetMapping("/all")
    @Operation(summary = "设备列表（管理员）")
    @RequiresPermissions("sys:role:superAdmin")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", required = true),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", required = true),
    })
    public Result<PageData<DeviceEntity>> adminDeviceList(
            @Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<DeviceEntity> page = deviceService.adminDeviceList(params);
        return new Result<PageData<DeviceEntity>>().ok(page);
    }
}