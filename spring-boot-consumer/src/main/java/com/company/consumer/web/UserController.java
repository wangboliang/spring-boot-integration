package com.company.consumer.web;

import com.company.consumer.entity.User;
import com.company.consumer.model.request.UserPageParam;
import com.company.consumer.model.response.BaseResponse;
import com.company.consumer.model.response.PageDataResponse;
import com.company.consumer.model.response.PageResponse;
import com.company.consumer.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangliang
 * @since 2017-07-05
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody User vo) {
        return userService.add(vo);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody User vo) {
        return userService.update(vo);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody User vo) {
        return userService.query(vo);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody User vo) {
        return userService.delete(vo);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResponse list(@RequestBody UserPageParam vo) {
        return userService.list(vo);
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody UserPageParam vo) {
        return userService.page(vo);
    }
}
