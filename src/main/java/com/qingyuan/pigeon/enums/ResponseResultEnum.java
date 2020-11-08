package com.qingyuan.pigeon.enums;

/**
 * 返回结果数据字典
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/1/31 11:12
 */
public enum ResponseResultEnum {
    FAILED(0, "失败"),
    SUCCESS(1, "成功"),
    /**
     * 参数错误
     */
    PARAM_IS_INVALID(0, "参数无效"),
    PARAM_IS_BLANK(0, "参数为空"),
    PARAM_TYPE_BIND_ERROR(0, "参数类型错误"),
    /**
     * 用户错误
     */
    USER_LOGIN_ERROR(0, "账号不存在或密码错误"),
    USER_NO_TOKEN(0, "用户token为空"),
    USER_NO_PERMISSION(0, "用户权限不足"),
    USER_LOGIN_SUCCESS(1, "登录成功");


    /**
     * 返回结果代码
     */
    private Integer code;
    /**
     * 返回具体信息
     */
    private String msg;

    ResponseResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
