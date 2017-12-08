package com.consumer.utils;

import com.consumer.enums.ReturnCodeEnum;
import com.consumer.model.response.BaseResponse;
import com.consumer.model.response.PageDataResponse;
import com.consumer.model.response.PageResponse;

/**
 * <p>
 * Response工具类
 * </p>
 *
 * @author wangliang
 * @since 2017/7/6
 */
public class BackResponseUtil {

    public static BaseResponse getBaseResponse(Integer code) {
        BaseResponse response = new BaseResponse();
        if (ReturnCodeEnum.CODE_1000.getCode().equals(code)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            response.setMessage(ReturnCodeEnum.CODE_1000.getValue());
        } else if (ReturnCodeEnum.CODE_1001.getCode().equals(code)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1001.getCode());
            response.setMessage(ReturnCodeEnum.CODE_1001.getValue());
        } else if (ReturnCodeEnum.CODE_1002.getCode().equals(code)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1002.getCode());
            response.setMessage(ReturnCodeEnum.CODE_1002.getValue());
        } else if (ReturnCodeEnum.CODE_1003.getCode().equals(code)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1003.getCode());
            response.setMessage(ReturnCodeEnum.CODE_1003.getValue());
        } else if (ReturnCodeEnum.CODE_1004.getCode().equals(code)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1004.getCode());
            response.setMessage(ReturnCodeEnum.CODE_1004.getValue());
        } else if (ReturnCodeEnum.CODE_1006.getCode().equals(code)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(ReturnCodeEnum.CODE_1006.getValue());
        } else if (ReturnCodeEnum.CODE_1008.getCode().equals(code)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1008.getCode());
            response.setMessage(ReturnCodeEnum.CODE_1008.getValue());
        } else if (ReturnCodeEnum.CODE_1009.getCode().equals(code)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1009.getCode());
            response.setMessage(ReturnCodeEnum.CODE_1009.getValue());
        } else if (ReturnCodeEnum.CODE_1010.getCode().equals(code)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1010.getCode());
            response.setMessage(ReturnCodeEnum.CODE_1010.getValue());
        } else if (ReturnCodeEnum.CODE_1005.getCode().equals(code)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage(ReturnCodeEnum.CODE_1005.getValue());
        }

        return response;
    }

    public static PageDataResponse getPageDataResponse(Integer code) {
        PageDataResponse pageDataResponse = new PageDataResponse();
        if (ReturnCodeEnum.CODE_1000.getCode().equals(code)) {
            pageDataResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            pageDataResponse.setMessage(ReturnCodeEnum.CODE_1000.getValue());
        } else if (ReturnCodeEnum.CODE_1002.getCode().equals(code)) {
            pageDataResponse.setReturnCode(ReturnCodeEnum.CODE_1002.getCode());
            pageDataResponse.setMessage(ReturnCodeEnum.CODE_1002.getValue());
        } else if (ReturnCodeEnum.CODE_1006.getCode().equals(code)) {
            pageDataResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            pageDataResponse.setMessage(ReturnCodeEnum.CODE_1006.getValue());
        } else if (ReturnCodeEnum.CODE_1005.getCode().equals(code)) {
            pageDataResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            pageDataResponse.setMessage(ReturnCodeEnum.CODE_1005.getValue());
        }

        return pageDataResponse;
    }

    public static PageResponse getPageResponse(Integer code) {
        PageResponse pageResponse = new PageResponse();
        if (ReturnCodeEnum.CODE_1000.getCode().equals(code)) {
            pageResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            pageResponse.setMessage(ReturnCodeEnum.CODE_1000.getValue());
        } else if (ReturnCodeEnum.CODE_1002.getCode().equals(code)) {
            pageResponse.setReturnCode(ReturnCodeEnum.CODE_1002.getCode());
            pageResponse.setMessage(ReturnCodeEnum.CODE_1002.getValue());
        } else if (ReturnCodeEnum.CODE_1006.getCode().equals(code)) {
            pageResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            pageResponse.setMessage(ReturnCodeEnum.CODE_1006.getValue());
        } else if (ReturnCodeEnum.CODE_1005.getCode().equals(code)) {
            pageResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            pageResponse.setMessage(ReturnCodeEnum.CODE_1005.getValue());
        }

        return pageResponse;
    }
}
