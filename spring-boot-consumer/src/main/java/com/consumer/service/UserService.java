package com.consumer.service;

import com.consumer.entity.User;
import com.consumer.enums.ReturnCodeEnum;
import com.consumer.model.request.UserPageParam;
import com.consumer.model.response.BaseResponse;
import com.consumer.model.response.PageDataResponse;
import com.consumer.model.response.PageResponse;
import com.consumer.rpc.UserRpcService;
import com.consumer.utils.BackResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

;

/**
 * <p>
 * service业务层
 * </p>
 *
 * @author wangliang
 * @since 2017-07-05
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private UserRpcService userRpcService;

    public BaseResponse add(User request) {
        BaseResponse response;
        //业务操作
        log.info("add User " + request);
        try {
            response = userRpcService.add(request);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1004.getCode());
            response.setMessage(ReturnCodeEnum.CODE_1004.getValue());
        }
        return response;
    }

    public BaseResponse update(User request) {
        BaseResponse response;
        //业务操作
        log.info("update User " + request);
        try {
            response = userRpcService.update(request);
            log.debug("update User back " + response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1004.getCode());
            response.setMessage(ReturnCodeEnum.CODE_1004.getValue());
        }
        return response;
    }

    public BaseResponse query(User request) {
        BaseResponse response;
        //业务操作
        log.info("query User " + request);
        try {
            response = userRpcService.query(request);
            log.info("query User back" + response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1004.getCode());
            response.setMessage(ReturnCodeEnum.CODE_1004.getValue());
        }
        return response;
    }

    public BaseResponse delete(User request) {
        BaseResponse response;
        //业务操作
        log.info("delete User " + request);
        try {
            response = userRpcService.delete(request);
            log.debug("delete User back " + response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1004.getCode());
            response.setMessage(ReturnCodeEnum.CODE_1004.getValue());
        }
        return response;
    }

    public PageDataResponse list(UserPageParam request) {
        PageDataResponse response;
        //业务操作
        log.info("User list method request " + request);
        if (null == request) {
            response = BackResponseUtil.getPageDataResponse(ReturnCodeEnum.CODE_1004.getCode());
        } else {
            log.info("User list method request " + request);
            response = userRpcService.list(request);
        }
        return response;
    }

    public PageResponse page(UserPageParam request) {
        PageResponse response;
        //业务操作
        log.info("User page method request " + request);
        if (null == request) {
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1004.getCode());
        } else {
            log.info("User page method request " + request);
            response = userRpcService.page(request);
        }
        return response;
    }
}
