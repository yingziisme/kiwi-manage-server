package com.hikari.kiwi.modules.kiwi.controller;

import io.reactivex.Flowable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.common.page.TokenDTO;
import com.hikari.kiwi.llm.ModelFactory;
import com.hikari.kiwi.llm.bigmodel.BigModelClient;
import com.hikari.kiwi.modules.kiwi.dto.KiwiDialogSetting;
import com.hikari.kiwi.modules.kiwi.dto.KiwiDialogSettingFindReq;
import com.hikari.kiwi.modules.kiwi.dto.KiwiDialogSettingReq;
import com.hikari.kiwi.modules.kiwi.dto.KiwiDialogTestDTO;
import com.hikari.kiwi.modules.kiwi.service.KiwiDialogSettingService;
import com.hikari.kiwi.modules.security.dto.LoginDTO;

@Slf4j
@RestController
@RequestMapping("/kiwi/dialog/setting")
@Tag(name = "对话配置相关接口", description = "对话配置相关接口")
public class KiwiDialogSettingController {

    @Autowired
    private KiwiDialogSettingService kiwiDialogSettingService;


    @PostMapping("/find")
    @Operation(summary = "对话设定查看")
    public BaseResponse<KiwiDialogSetting> findDialog(@RequestBody KiwiDialogSettingFindReq req) {
        log.info("findDialog req: {}", req);
        return kiwiDialogSettingService.findDialog(req);
    }

    @PostMapping("/save")
    @Operation(summary = "对话设定编辑保存")
    public BaseResponse<KiwiDialogSetting> saveDialog(@RequestBody KiwiDialogSettingReq req) {

        log.info("saveDialog req: {}", req);
        return kiwiDialogSettingService.saveDialog(req);
    }

}
