package com.provider.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.provider.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 继承BaseMapper实现单表CRUD操作
 * </p>
 *
 * @author wangliang
 * @since 2017-07-05
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 自定义注入方法
     */
    int deleteAll();

    @Select("select test_id as id, name, age, test_type from user")
    List<User> selectListBySQL();
}