package com.company.provider.service;

import com.baomidou.mybatisplus.service.IService;
import com.company.provider.entity.User;
import com.company.provider.model.request.UserPageParam;
import com.company.provider.model.response.BaseResponse;
import com.company.provider.model.response.PageDataResponse;
import com.company.provider.model.response.PageResponse;

import java.util.List;

/**
 * <p>
 * service接口
 * </p>
 *
 * @author wangliang
 * @since 2017-07-05
 */
public interface IUserService extends IService<User> {

    BaseResponse add(User model);

    BaseResponse delete(User model);

    BaseResponse update(User model);

    BaseResponse query(User model);

    PageDataResponse list(UserPageParam pageParam);

    PageResponse page(UserPageParam pageParam);

    boolean deleteAll();

    public List<User> selectListBySQL();
}
