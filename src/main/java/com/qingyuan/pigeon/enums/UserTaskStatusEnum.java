package com.qingyuan.pigeon.enums;

/**
 * 用户在活动中的状态枚举类
 * @author 24605
 */
public enum UserTaskStatusEnum {
    USER_APPKYED(0,"已报名"),
    USER_CHECKED(1,"已签到");

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
}
