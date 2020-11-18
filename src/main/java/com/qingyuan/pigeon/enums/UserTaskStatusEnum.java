package com.qingyuan.pigeon.enums;

/**
 * 用户在活动中的状态枚举类
 * @author 24605
 */
public enum UserTaskStatusEnum {
    USER_INVITED(0,"被邀请"),
    USER_APPLIED(1,"已报名"),
    USER_CHECK_IN(2,"已签到"),
    USER_CHECK_OUT(3, "已签退");

    /**
     * 用户活动状态id
     */
    private Integer userTaskStatusId;

    /**
     * 用户活动状态
     */
    private String userTaskStatus;

    UserTaskStatusEnum(Integer userTaskStatusId, String userTaskStatus) {
        this.userTaskStatusId = userTaskStatusId;
        this.userTaskStatus = userTaskStatus;
    }

    public Integer getUserTaskStatusId() {
        return userTaskStatusId;
    }

    public void setUserTaskStatusId(Integer userTaskStatusId) {
        this.userTaskStatusId = userTaskStatusId;
    }

    public String getUserTaskStatus() {
        return userTaskStatus;
    }

    public void setUserTaskStatus(String userTaskStatus) {
        this.userTaskStatus = userTaskStatus;
    }

    public String getMsg() {
        return this.userTaskStatus;
    }
}
