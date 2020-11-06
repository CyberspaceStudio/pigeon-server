package com.qingyuan.pigeon.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 任务
 * @program: pigeon_server
 * @author: GuoShuSong
 * @create: 2020-10-22 15:03
 **/
@Data
public class Task {


    /**
     * 任务id
     */
    private Integer taskId;

    /**
     * 任务人数下限
     */
    private Integer taskCountMin;

    /**
     * 任务人数上限
     */
    private Integer taskCountMax;

    /**
     * 任务开始时间
     */
    @JsonFormat(locale = "yyyy-MM-dd HH:mm")
    private String taskStartTime;

    /**
     * 任务结束时间
     */
    @JsonFormat(locale = "yyyy-MM-dd HH:mm")
    private String taskEndTime;


    /**
     * 任务状态
     */
    private Integer taskStatusId;

    //经纬度的定义字段类型使用 decimal(10,6) ， 在Java 中映射成Double.
    /**
     * 签到经度  一共10位，小数点后有6位
     */
    private Double checkLongitude;

    /**
     * 签到纬度 一共10位，小数点后有6位
     */
    private Double checkLatitude;

    /**
     * 任务类型
     */
    private String taskType;
}
