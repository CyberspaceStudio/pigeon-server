package com.qingyuan.pigeon.mapper;

import com.qingyuan.pigeon.pojo.Task;

/**
 * 对应数据库 task_member task
 *
 * @author 24605
 */
public interface TaskMapper {

    /**
     * 新增任务
     * @param task
     * @return
     */
    int insertTask(Task task);
}
