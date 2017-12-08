package com.consumer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 返回信息枚举
 * </p>
 *
 * @author wangliang
 * @since 2017/7/6
 */
@Getter
@AllArgsConstructor
public enum ReturnCodeEnum {
    CODE_1000(Integer.valueOf(1000), "成功"),
    CODE_1001(Integer.valueOf(1001), "无权限访问"),
    CODE_1002(Integer.valueOf(1002), "信息不存在"),
    CODE_1003(Integer.valueOf(1003), "支付方式错误"),
    CODE_1004(Integer.valueOf(1004), "服务器处理异常"),
    CODE_1005(Integer.valueOf(1005), "失败"),
    CODE_1006(Integer.valueOf(1006), "参数不全"),
    CODE_1007(Integer.valueOf(1007), "请求超时"),
    CODE_1008(Integer.valueOf(1008), "信息已存在"),
    CODE_1009(Integer.valueOf(1009), "登录状态已失效, 请重新登录"),
    CODE_1010(Integer.valueOf(1010), "验证失败");

    private Integer code;
    private String value;
}
