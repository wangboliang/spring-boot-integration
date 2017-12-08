package com.consumer.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 查询返回包装类
 * </p>
 *
 * @author wangliang
 * @since 2017/7/6
 */
@Getter
@Setter
@ToString
public class BaseResponse<T> {
    //返回编码
    private Integer returnCode;
    //返回信息
    private String message;
    //返回数据
    private T dataInfo;
}
