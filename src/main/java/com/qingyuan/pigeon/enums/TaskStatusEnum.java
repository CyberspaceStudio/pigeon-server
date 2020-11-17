package com.qingyuan.pigeon.enums;

/**
 * 活动状态枚举类
 * @author 24605
 */
public enum TaskStatusEnum {
    TASK_CREATED(0,"已创建"),
    TASK_APPLY_TIME(1,"已过报名时间"),
    TASK_CHECK_TIME_PASSES(2,"已过签到时间"),
    TASK_EXPIRED(3,"已过期");

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

    public Integer getTaskStatusId() {
        return taskStatusId;
    }

    public void setTaskStatusId(Integer taskStatusId) {
        this.taskStatusId = taskStatusId;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
}
