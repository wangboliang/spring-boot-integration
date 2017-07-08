package com.company.consumer.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 分页查询请求包装类
 * </p>
 *
 * @author wangliang
 * @since 2017-07-05
 */
@Getter
@Setter
@ToString
public class UserPageParam {
    //每页条数
    private Integer pageSize;
    //当前页数
    private Integer PageNumber;
}
