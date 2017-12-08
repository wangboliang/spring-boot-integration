package com.consumer.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wangliang
 * @since 2017-07-05
 */

@Getter
@Setter
@ToString
public class User {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private Long createdTime;
    private Long createdBy;
    private Long updatedTime;
    private Long updatedBy;
    private Integer deleted;


}
