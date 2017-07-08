package com.company.provider.rpc;

import com.company.provider.api.UserProto;
import com.company.provider.api.UserProto.UserListResponse;
import com.company.provider.api.UserProto.UserPageResponse;
import com.company.provider.api.UserProto.UserResponse;
import com.company.provider.api.UserServiceGrpc;
import com.company.provider.entity.User;
import com.company.provider.model.request.UserPageParam;
import com.company.provider.model.response.BaseResponse;
import com.company.provider.model.response.PageResponse;
import com.company.provider.service.IUserService;
import com.company.provider.utils.GRpcMessageConverter;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

/**
 * <p>
 *  rpc层数据传输
 * </p>
 *
 * @author wangliang
 * @since 2017-07-05
 */
@Slf4j
@GrpcService(UserServiceGrpc.class)
public class UserRpc extends UserServiceGrpc.UserServiceImplBase {

    private IUserService service;

    public UserRpc(IUserService service) {
        this.service = service;
    }

    @Override
    public void add(UserProto.UserDTO request, StreamObserver<UserResponse> responseObserver) {
        User model = (User) GRpcMessageConverter.fromGRpcMessage(request, User.class);
        BaseResponse baseResponse = service.add(model);
        UserResponse response = (UserResponse) GRpcMessageConverter.toGRpcMessage(baseResponse, UserResponse.newBuilder());
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void query(UserProto.UserDTO request, StreamObserver<UserResponse> responseObserver) {
        User model = (User) GRpcMessageConverter.fromGRpcMessage(request, User.class);
        BaseResponse baseResponse = service.query(model);
        UserResponse response = (UserResponse) GRpcMessageConverter.toGRpcMessage(baseResponse, UserResponse.newBuilder());
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void update(UserProto.UserDTO request, StreamObserver<UserResponse> responseObserver) {
        User model = (User) GRpcMessageConverter.fromGRpcMessage(request, User.class);
        BaseResponse baseResponse = service.update(model);
        UserResponse response = (UserResponse) GRpcMessageConverter.toGRpcMessage(baseResponse, UserResponse.newBuilder());
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(UserProto.UserDTO request, StreamObserver<UserResponse> responseObserver) {
        //业务操作
        User model = (User) GRpcMessageConverter.fromGRpcMessage(request, User.class);
        BaseResponse baseResponse = service.delete(model);
        UserResponse response = (UserResponse) GRpcMessageConverter.toGRpcMessage(baseResponse, UserResponse.newBuilder());
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void list(UserProto.UserRequest request, StreamObserver<UserListResponse> responseObserver) {
        UserListResponse response = null;
        UserPageParam pageParam = (UserPageParam) GRpcMessageConverter.fromGRpcMessage(request, UserPageParam.class);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    @Override
    public void page(UserProto.UserRequest request, StreamObserver<UserPageResponse> responseObserver) {
        UserPageParam pageParam = (UserPageParam) GRpcMessageConverter.fromGRpcMessage(request, UserPageParam.class);
        PageResponse selectPage = service.page(pageParam);
        UserPageResponse response = (UserPageResponse) GRpcMessageConverter.toGRpcMessage(selectPage, UserPageResponse.newBuilder());
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
