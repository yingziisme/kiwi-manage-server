package com.hikari.kiwi.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.hikari.kiwi.common.constant.ErrorCode;

import java.util.ArrayList;
import java.util.List;

@Data
public class BasePageResponse<T> {

    @Schema(description = "总数量")
    private Long totalCount;
    @Schema(description = "总页数")
    private Integer totalPage;
    @Schema(description = "分页大小")
    private Integer pageSize;
    @Schema(description = "分页页码")
    private Integer pageNumber;
    @Schema(description = "数据")
    private List<T> dataList;


    BasePageResponse(Long totalCount, Integer totalPage, Integer pageSize, Integer pageNumber, List<T> dataList) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.dataList = dataList;
    }

    public static <K> BaseResponse<BasePageResponse<K>> buildSuccessPageResponse(String requestId) {

        return BaseResponse.buildSuccessResponse(requestId, new BasePageResponse<>(0L, 0, 0, 0, new ArrayList<>()));
    }

    public static <K> BaseResponse<BasePageResponse<K>> buildSuccessPageResponse(String requestId, Long totalCount, Integer totalPage, Integer pageSize, Integer pageNumber, List<K> dataList) {

        return BaseResponse.buildSuccessResponse(requestId, new BasePageResponse<>(totalCount, totalPage, pageSize, pageNumber, dataList));
    }

    public static <K> BaseResponse<BasePageResponse<K>> buildFailurePageResponse(ErrorCode code, String requestId, Long totalCount, Integer totalPage, Integer pageSize, Integer pageNumber, List<K> dataList) {

        return BaseResponse.buildFailureResponse(code, requestId, new BasePageResponse<>(totalCount, totalPage, pageSize, pageNumber, dataList));
    }

}
