package com.qingyuan.pigeon.enums;

/**
 * 鸽子蛋来源枚举类
 * @program: pigeon_server
 * @author: GuoShuSong
 * @create: 2020-11-18 20:09
 **/
public enum PigeonEggSourceEnum {
    TASK_CHECK_IN(0,"任务签到"),
    DAYIY_CHECK_IN(1,"每日签到"),
    CONTINUOUS_CHECK_IN(2,"连续签到");

    /**
     * 鸽子蛋来源id
     */
    private Integer pigeonSourceId;

    /**
     * 鸽子蛋来源
     */
    private String pigeonSource;

    public Integer getPigeonSourceId() {
        return pigeonSourceId;
    }

    public void setPigeonSourceId(Integer pigeonSourceId) {
        this.pigeonSourceId = pigeonSourceId;
    }

    public String getPigeonSource() {
        return pigeonSource;
    }

    public void setPigeonSource(String pigeonSource) {
        this.pigeonSource = pigeonSource;
    }

    PigeonEggSourceEnum(Integer pigeonSourceId, String pigeonSource) {
        this.pigeonSourceId = pigeonSourceId;
        this.pigeonSource = pigeonSource;
    }
}
