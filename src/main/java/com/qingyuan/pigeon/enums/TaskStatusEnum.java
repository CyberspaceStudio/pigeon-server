package com.qingyuan.pigeon.enums;

/**
 * 活动状态枚举类
 * @author 24605
 */
public enum TaskStatusEnum {


    /**
     * 报名时间: 任务开始前2个小时之外
     * 签到时间: 任务开始前20分钟,任务开始后10分钟
     * 签退时间: 任务结束后30分钟之内
     * 过期时间: 任务结束后30分钟
     */
    TASK_CREATED(0,"已创建"),
    TASK_EXPIRED(1,"已过期");

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
