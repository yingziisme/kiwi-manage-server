package com.hikari.kiwi.modules.kiwi.service;

import com.hikari.kiwi.modules.kiwi.dto.KiwiDialogMessageReq;
import io.reactivex.Flowable;
import org.springframework.http.codec.ServerSentEvent;

public interface KiwiDialogService {
    Flowable<ServerSentEvent<String>> getResponse(KiwiDialogMessageReq dto);
}
