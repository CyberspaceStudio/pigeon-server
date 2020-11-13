package com.qingyuan.pigeon.service;

import com.qingyuan.pigeon.pojo.Task;
import com.qingyuan.pigeon.utils.UniversalResponseBody;

import java.util.List;

/**
 * 任务相关接口
 * @author 24605
 */
public interface TaskService {
    /**
     * 新增任务
     * @param task
     * @return
     * @apiNote taskId以及taskStatusId不用填写
     */
    UniversalResponseBody<Task> addTask(Task task);

    /**
     * 获取用户所有的任务
     * @param userId
     * @return
     */
    UniversalResponseBody<List<Task>> getUserTasks(Integer userId);

    /**
     * 报名任务
     * @param userId
     * @param taskId
     * @return
     */
    UniversalResponseBody<Task> applyTask(Integer userId,Integer taskId);

    /**
     * 检查用户签到地点
     * @param checkLongitude 用户签到处的经度
     * @param checkLatitude 用户签到处的纬度
     * @param taskId
     * @param userId
     * @return
     */
    UniversalResponseBody<Task> taskCheckIn(Double checkLongitude,Double checkLatitude,Integer taskId,Integer userId);
}
