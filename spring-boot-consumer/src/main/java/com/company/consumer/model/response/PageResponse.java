package com.company.consumer.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * <p>
 * 批量查询返回包装类
 * </p>
 *
 * @author wangliang
 * @since 2017/7/6
 */
@Getter
@Setter
@ToString
public class PageResponse<T> {
    private List<T> records;
    private int total;
    private int size;
    private int pages;
    private int current;
    private boolean searchCount;
    private boolean optimizeCount;
    private String orderByField;
    private boolean isAsc;
    private Integer returnCode;
    private String message;
}
