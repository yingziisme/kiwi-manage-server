package com.hikari.kiwi.modules.kiwi.service;

import com.hikari.kiwi.common.dto.BasePageResponse;
import com.hikari.kiwi.common.dto.BaseResponse;
import com.hikari.kiwi.modules.agent.dto.AiAgentDTO;
import com.hikari.kiwi.modules.kiwi.dto.KiwiAiAgentMessageReq;
import com.hikari.kiwi.modules.kiwi.dto.KiwiAiAgentPageReq;

public interface KiwiAiAgentService {

    BaseResponse<BasePageResponse<AiAgentDTO>> getAiAgentsByPage(KiwiAiAgentPageReq req);

    BaseResponse<String> getCurrentMessage(KiwiAiAgentMessageReq req);
}
