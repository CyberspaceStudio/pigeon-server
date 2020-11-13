package com.qingyuan.pigeon.mapper;

import com.qingyuan.pigeon.pojo.DO.TaskMember;
import com.qingyuan.pigeon.pojo.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 查询用户所有任务
     * @param userId
     * @return
     */
    List<Task> getUserTasks(Integer userId);

    /**
     * 查找指定任务
     * @param taskId
     * @return
     */
    Task getTaskById(Integer taskId);

    /**
     * 用户添加任务
     * @param taskMember
     * @return
     */
    int addUserTask(TaskMember taskMember);

    /**
     * 更新任务参加人数
     * @param taskId
     * @param taskApplyCount
     * @return
     */
    int updateTaskApplyCount(@Param("taskId") Integer taskId, @Param("taskApplyCount") Integer taskApplyCount);

    /**
     * 更新任务
     * @param taskMember
     * @return
     */
    int updateTaskMember(TaskMember taskMember);

    /**
     * 查询用户任务的状态
     * @param userId
     * @param taskId
     * @return
     */
    String getUserTaskStatus(@Param("userId") Integer userId, @Param("taskId") Integer taskId);
}
