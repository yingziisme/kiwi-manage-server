package com.hikari.kiwi.modules.kiwi.controller;

import com.alibaba.fastjson.JSON;
import com.hikari.kiwi.modules.utils.TokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.hikari.kiwi.common.constant.ErrorCode;
import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.common.page.TokenDTO;
import com.hikari.kiwi.common.utils.Result;
import com.hikari.kiwi.modules.security.dto.CaptchaDTO;
import com.hikari.kiwi.modules.security.dto.LoginDTO;
import com.hikari.kiwi.modules.security.password.PasswordUtils;
import com.hikari.kiwi.modules.security.service.CaptchaService;
import com.hikari.kiwi.modules.security.service.SysUserTokenService;
import com.hikari.kiwi.modules.sys.dto.SysUserDTO;
import com.hikari.kiwi.modules.sys.service.SysUserService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequestMapping("/kiwi/common")
@Tag(name = "登录相关接口", description = "登录相关接口")
public class KiwiCommonController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private TokenUtils tokenUtils;


    @GetMapping("/captcha")
    @Operation(summary = "验证码")
    public void captcha(HttpServletResponse response, String captchaId) throws IOException {
        if (StringUtils.isEmpty(captchaId)) {
            BaseResponse baseResponse = BaseResponse.buildFailureResponse("验证码错误，UUID为空", ErrorCode.PARAM_ERROR.getCode(), captchaId);

            String newResponseBody = JSON.toJSONString(baseResponse);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            byte[] responseBodyData = newResponseBody.getBytes(StandardCharsets.UTF_8);
            ServletOutputStream out = response.getOutputStream();
            out.write(responseBodyData);
            return;
        }

        //生成验证码
        captchaService.create(response, captchaId);
    }

    @PostMapping("/login")
    @Operation(summary = "登录")
    public BaseResponse<TokenDTO> login(@RequestBody LoginDTO login) {
        // 验证是否正确输入验证码
        boolean validate = captchaService.validate(login.getCaptchaId(), login.getCaptcha());
        if (!validate) {
            return BaseResponse.buildFailureResponse("验证码错误，请重新获取", ErrorCode.PARAM_ERROR.getCode(), login.getRequestId());
        }
        // 按照用户名获取用户
        SysUserDTO userDTO = sysUserService.getByUsername(login.getUsername());
        // 判断用户是否存在
        if (userDTO == null) {
            return BaseResponse.buildFailureResponse("请检测用户和密码是否输入错误", ErrorCode.PARAM_ERROR.getCode(), login.getRequestId());
        }
        // 判断密码是否正确，不一样则进入if
        if (!PasswordUtils.matches(login.getPassword(), userDTO.getPassword())) {
            return BaseResponse.buildFailureResponse("请检测用户和密码是否输入错误", ErrorCode.PARAM_ERROR.getCode(), login.getRequestId());
        }
        Result<TokenDTO> result = sysUserTokenService.createToken(userDTO.getId());

        tokenUtils.setToken(result.getData().getToken(), userDTO.getId());
        return BaseResponse.buildSuccessResponse(login.getRequestId(), result.getData());
    }
    @PostMapping("/register")
    @Operation(summary = "注册")
    public BaseResponse<Void> register(@RequestBody LoginDTO login) {
        // 验证是否正确输入验证码
        boolean validate = captchaService.validate(login.getCaptchaId(), login.getCaptcha());
        if (!validate) {
            return BaseResponse.buildFailureResponse("验证码错误，请重新获取", ErrorCode.PARAM_ERROR.getCode(), login.getRequestId());
        }
        // 按照用户名获取用户
        SysUserDTO userDTO = sysUserService.getByUsername(login.getUsername());
        if (userDTO != null) {
            return BaseResponse.buildFailureResponse("此手机号码已经注册过", ErrorCode.PARAM_ERROR.getCode(), login.getRequestId());
        }
        userDTO = new SysUserDTO();
        userDTO.setUsername(login.getUsername());
        userDTO.setPassword(login.getPassword());
        sysUserService.save(userDTO);
        return BaseResponse.buildSuccessResponse(login.getRequestId());

    }
}
