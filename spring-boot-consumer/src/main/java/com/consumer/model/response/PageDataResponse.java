package com.consumer.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * <p>
 * 分页查询返回包装类
 * </p>
 *
 * @author wangliang
 * @since 2017/7/6
 */
@Getter
@Setter
@ToString
public class PageDataResponse<T> {
    //每页条数
    private Integer pageSize;
    //当前页数
    private Integer pageNumber;
    //总记录数
    private Integer totalRecord = Integer.valueOf(0);
    //返回编码
    private Integer returnCode;
    //返回信息
    private String message;
    //返回数据
    private List<T> pageData;
}
