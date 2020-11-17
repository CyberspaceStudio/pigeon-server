package com.qingyuan.pigeon.pojo.DO;

import lombok.Data;

import java.util.Date;

/**
 * 对应数据库表:task_member
 * @program: pigeon_server
 * @author: GuoShuSong
 * @create: 2020-11-08 11:09
 **/
@Data
public class TaskMember {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 任务id
     */
    private Integer taskId;

    /**
     * 用户签到经度
     */
    private Double checkInLongitude;

    /**
     * 用户签到纬度
     */
    private Double checkInLatitude;

    /**
     * 用户签退经度
     */
    private Double checkOutLongitude;

    /**
     * 用户签退纬度
     */
    private Double checkOutLatitude;

    /**
     * 用户状态id
     */
    private String userTaskStatus;

    /**
     * 签到时间
     * @ignore
     */
    private Date checkInTime;

    /**
     * 签退时间
     */
    private Date checkOutTime;


    /**
     * 用户所得鸽子蛋数量
     */
    private Integer userPigeonEggCount;
}
