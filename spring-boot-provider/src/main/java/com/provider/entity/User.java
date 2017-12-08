package com.provider.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 用户信息实体类
 * </p>
 *
 * @author wangliang
 * @since 2017-07-05
 */
@Getter
@Setter
@ToString
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    @TableField("created_time")
    private Long createdTime;
    @TableField("created_by")
    private Long createdBy;
    @TableField("updated_time")
    private Long updatedTime;
    @TableField("updated_by")
    private Long updatedBy;
    private Integer deleted;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
