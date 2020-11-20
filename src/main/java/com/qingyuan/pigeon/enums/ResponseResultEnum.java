package com.qingyuan.pigeon.enums;

import com.qingyuan.pigeon.utils.UniversalResponseBody;

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
     * 登录注册错误
     */
    USER_LOGIN_ERROR(0, "账号不存在或密码错误"),
    USER_NO_TOKEN(0, "登录失效,请重新登录"),
    USER_NO_PERMISSION(0, "用户权限不足"),
    VERITY_CODE_EXPIRED_OR_INCORRECT(0, "验证码不正确或已失效"),
    USER_IS_EXISTED(0, "用户已存在"),
    USER_NOT_HAVE_TEAM(0,"未参加任何团队"),
    /**
     * 团队相关错误
     */
    TEAM_MEMBER_REACH_MAX(0, "团队人数达到上限"),
    TEAM_MEMBER_IS_EXISTED(0, "团队已存在该成员"),
    /**
     * 任务相关错误
     */
    TASK_MEMBER_REACH_MAX(0, "任务人数已达上限"),
    NOT_IN_CHECK_LOCATION(0, "不在签到范围内"),
    CHECK_IN_ALREADY(0, "您已签到过，不能重复签到"),
    CHECK_OUT_ALREADY(0, "您已签退过，不能重复签退"),
    NOT_AT_CHECK_IN_TIME(0, "未到签到时间或签到时间已过"),
    NOT_AT_CHECK_OUT_TIME(0, "未到签退时间或签退时间已过"),
    NOT_AT_APPLY_TIME(0, "未到报名时间或报名时间已过"),
    FILE_IO_EXCEPTION(0,"服务器文件错误"),
    DUPLICATE_CHECK_IN(0,"今天已签到");


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
