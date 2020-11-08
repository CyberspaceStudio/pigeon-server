package com.qingyuan.pigeon.enums;

/**
 * 活动类型枚举类
 * @program: pigeon_server
 * @author: GuoShuSong
 * @create: 2020-11-08 11:34
 **/
public enum  ActivityTypeEnum {

    RUN(0,"跑步"),
    STUDY(1,"自习");

    /**
     * 活动类型
     */
    private String activityType;

    /**
     * 活动类型id
     */
    private Integer activityTypeId;


    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Integer getActivityTypeId() {
        return activityTypeId;
    }

    public void setActivityTypeId(Integer activityTypeId) {
        this.activityTypeId = activityTypeId;
    }

    ActivityTypeEnum(Integer activityTypeId,String activityType) {
        this. activityType = activityType;
        this.activityTypeId = activityTypeId;
    }
}
