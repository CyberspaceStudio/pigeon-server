package com.qingyuan.pigeon.enums;

/**
 * 活动状态枚举类
 * @author 24605
 */
public enum TaskStatusEnum {
    TASK_CREATED(0,"已创建"),
    TASK_EXPIRED(2,"已过期");

    /**
     * 活动状态对应的id
     */
    private Integer taskStatusId;
    ;
    /**
     * 活动状态
     */
    private String taskStatus;

    TaskStatusEnum(Integer taskStatusId, String taskStatus) {
        this.taskStatusId = taskStatusId;
        this.taskStatus = taskStatus;
    }
}
