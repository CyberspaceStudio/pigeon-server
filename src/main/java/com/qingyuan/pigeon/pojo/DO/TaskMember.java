package com.qingyuan.pigeon.pojo.DO;

import lombok.Data;

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
     * 用户签到的经度
     */
    private Double useCheckLongitude;

    /**
     * 用户签到纬度
     */
    private Double userCheckLatitude;


    /**
     * 用户状态id
     */
    private String userTaskStatus;

    /**
     * 用户所得鸽子蛋数量
     */
    private Integer userPigeonEggCount;
}
