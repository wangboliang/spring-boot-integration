package com.consumer.rpc;

import com.company.consumer.api.UserProto;
import com.company.consumer.api.UserProto.UserDTO;
import com.company.consumer.api.UserProto.UserListResponse;
import com.company.consumer.api.UserProto.UserPageResponse;
import com.company.consumer.api.UserProto.UserResponse;
import com.company.consumer.api.UserServiceGrpc.UserServiceBlockingStub;
import com.consumer.entity.User;
import com.consumer.model.request.UserPageParam;
import com.consumer.model.response.BaseResponse;
import com.consumer.model.response.PageDataResponse;
import com.consumer.model.response.PageResponse;
import com.consumer.utils.GRpcMessageConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * rpc层数据传输
 * </p>
 *
 * @author wangliang
 * @since 2017-07-05
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserRpcService {

    private UserServiceBlockingStub blockingStub;

    public BaseResponse add(User request) {
        UserDTO dto = (UserDTO) GRpcMessageConverter.toGRpcMessage(request, UserDTO.newBuilder());
        UserResponse response = blockingStub.add(dto);
        return (BaseResponse) GRpcMessageConverter.fromGRpcMessage(response, BaseResponse.class, User.class);
    }

    public BaseResponse update(User request) {
        UserDTO dto = (UserDTO) GRpcMessageConverter.toGRpcMessage(request, UserDTO.newBuilder());
        UserResponse response = blockingStub.update(dto);
        return (BaseResponse) GRpcMessageConverter.fromGRpcMessage(response, BaseResponse.class, User.class);
    }

    public BaseResponse query(User request) {
        UserDTO dto = (UserDTO) GRpcMessageConverter.toGRpcMessage(request, UserDTO.newBuilder());
        UserResponse response = blockingStub.query(dto);
        return (BaseResponse) GRpcMessageConverter.fromGRpcMessage(response, BaseResponse.class, User.class);
    }

    public BaseResponse delete(User request) {
        UserDTO dto = (UserDTO) GRpcMessageConverter.toGRpcMessage(request, UserDTO.newBuilder());
        UserResponse response = blockingStub.delete(dto);
        return (BaseResponse) GRpcMessageConverter.fromGRpcMessage(response, BaseResponse.class, User.class);
    }

    public PageDataResponse list(UserPageParam request) {
        UserProto.UserRequest dto = (UserProto.UserRequest) GRpcMessageConverter.toGRpcMessage(request, UserProto.UserRequest.newBuilder());
        UserListResponse listResponse = blockingStub.list(dto);
        return (PageDataResponse) GRpcMessageConverter.fromGRpcMessage(listResponse, PageDataResponse.class, User.class);
    }

    public PageResponse page(UserPageParam request) {
        UserProto.UserRequest dto = (UserProto.UserRequest) GRpcMessageConverter.toGRpcMessage(request, UserProto.UserRequest.newBuilder());
        UserPageResponse listResponse = blockingStub.page(dto);
        return (PageResponse) GRpcMessageConverter.fromGRpcMessage(listResponse, PageResponse.class, User.class);
    }
}
