package com.hikari.kiwi.llm;

import io.reactivex.Flowable;
import org.springframework.http.codec.ServerSentEvent;

public interface BaseModelClient {


    Flowable<ServerSentEvent<String>> getResponse(String userId, String requestId, String msg);
}
