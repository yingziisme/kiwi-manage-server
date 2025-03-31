package com.hikari.kiwi.modules.kiwi.service;

import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.modules.kiwi.dto.KiwiDialogSetting;
import com.hikari.kiwi.modules.kiwi.dto.KiwiDialogSettingFindReq;
import com.hikari.kiwi.modules.kiwi.dto.KiwiDialogSettingReq;

public interface KiwiDialogSettingService {

    BaseResponse<KiwiDialogSetting> findDialog(KiwiDialogSettingFindReq req);

    BaseResponse<KiwiDialogSetting> saveDialog(KiwiDialogSettingReq req);
}
