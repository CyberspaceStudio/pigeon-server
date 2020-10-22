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

    /**
     * 签到经度
     */
    private String checkLongitude;

    /**
     * 签到纬度
     */
    private String checkLatitude;

    /**
     * 任务类型
     */
    private String taskType;
}
