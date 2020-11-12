package com.qingyuan.pigeon.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 任务
 * @program: pigeon_server
 * @author: GuoShuSong
 * @create: 2020-10-22 15:03
 **/
@Data
public class Task {


    /**
     * 任务id(数据库自动递增生成)
     */
    private Integer taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 所属团队id
     */
    private Integer teamId;

    /**
     * 任务人数上限
     */
    private Integer memberCountMax;

    /**
     * 任务活动地点
     */
    private String taskActivityLocation;

    /**
     * 任务开始时间
     */
    @JsonFormat(locale = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date taskStartTime;

    /**
     * 任务结束时间
     */
    @JsonFormat(locale = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date taskEndTime;

    /**
     * 任务状态(此项在插入数据库时会默认 已创建)
     */
    private String taskStatus;

    //经纬度的定义字段类型使用 decimal(10,7) ， 在Java 中映射成Double.
    /**
     * 签到经度  一共10位，小数点后有7位
     */
    private Double checkLongitude;

    /**
     * 签到纬度 一共10位，小数点后有7位
     */
    private Double checkLatitude;

    /**
     * 任务类型
     */
    private String activityType;

    /**
     * 任务报名人数
     */
    private Integer taskApplyCount;
}
