package com.qingyuan.pigeon.service;

import com.qingyuan.pigeon.pojo.Task;
import com.qingyuan.pigeon.utils.UniversalResponseBody;

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
}
