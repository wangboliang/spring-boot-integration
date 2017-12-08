package com.consumer.config;

import com.company.consumer.api.UserServiceGrpc;
import com.company.consumer.api.UserServiceGrpc.UserServiceBlockingStub;

import io.grpc.ManagedChannel;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * <p>
 *  rpc server连接配置
 * </p>
 *
 * @author wangliang
 * @since 2017-07-05
 */

@Configuration
@EnableAutoConfiguration
public class UserGrpcConfiguration {
    @GrpcClient("spring-boot-provider")
    private  ManagedChannel channel;

    @Bean
    public UserServiceBlockingStub userServiceBlockingStub() {
        return UserServiceGrpc.newBlockingStub(channel);
    }

}
