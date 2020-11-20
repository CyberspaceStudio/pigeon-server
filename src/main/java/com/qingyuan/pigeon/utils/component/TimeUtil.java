package com.qingyuan.pigeon.utils.component;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: qyl
 * @Date: 2020/11/17 21:27
 */
@Component
public class TimeUtil {

    /**
     * 报名时间: 任务开始前 2 个小时之外
     * 签到时间: 任务开始前 20 分钟,任务开始后 10 分钟
     * 签退时间: 任务结束后 30 分钟之内
     * 过期时间: 任务结束后 30 分钟
     */

    /**
     * 判断是否在签到时间内
     * @param taskStartTime
     * @param nowTime
     * @return
     */
    public static boolean isCheckInTime(Date taskStartTime, Date nowTime) {
        long interval = nowTime.getTime() - taskStartTime.getTime();
        return interval >= -(20 * 60 * 1000) && interval <= (10 * 60 * 1000);
    }

    /**
     * 判断是否在签退时间内
     * @param taskEndTime
     * @param nowTime
     * @return
     */
    public static boolean isCheckOutTime(Date taskEndTime, Date nowTime) {
        long interval = nowTime.getTime() - taskEndTime.getTime();
        return interval >= 0 && interval <= (30 * 60 * 1000);
    }

    /**
     * 判断报名任务的时间
     * @param taskStartTime
     * @param nowTime
     * @return
     */
    public static boolean isApplyTime(Date taskStartTime, Date nowTime) {
        long interval = taskStartTime.getTime() - nowTime.getTime();
        return interval > (2 * 60 * 60 * 1000);
    }
}
