package com.qingyuan.pigeon.service.impl;

import com.qingyuan.pigeon.enums.ResponseResultEnum;
import com.qingyuan.pigeon.enums.TaskStatusEnum;
import com.qingyuan.pigeon.enums.UserTaskStatusEnum;
import com.qingyuan.pigeon.mapper.TaskMapper;
import com.qingyuan.pigeon.mapper.TeamMemberMapper;
import com.qingyuan.pigeon.mapper.UserMessageMapper;
import com.qingyuan.pigeon.pojo.DO.TaskMember;
import com.qingyuan.pigeon.pojo.Task;
import com.qingyuan.pigeon.service.TaskService;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import com.qingyuan.pigeon.utils.component.GeoDistUtil;
import com.qingyuan.pigeon.utils.component.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 任务相关接口
 * @author 24605
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskMapper taskMapper;
    @Resource
    private UserMessageMapper userMessageMapper;
    @Resource
    private TeamMemberMapper teamMemberMapper;

    @Override
    @Transactional
    public UniversalResponseBody<Task> addTask(Task task) {
        //新增任务
        int count = taskMapper.insertTask(task);
        if(count > 0){
            return new UniversalResponseBody<>(ResponseResultEnum.SUCCESS.getCode(),ResponseResultEnum.SUCCESS.getMsg(),task);
        }else{
            return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(),ResponseResultEnum.FAILED.getMsg());
        }
    }

    @Override
    public UniversalResponseBody<List<Task>> getUserTasks(Integer userId) {
        // 通过userId来获取该用户的所有任务
        List<Task> tasks = taskMapper.getUserTasks(userId);
        return new UniversalResponseBody<>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), tasks);
    }

    @Override
    @Transactional
    public UniversalResponseBody<Task> applyTask(Integer userId, Integer taskId) {
        // 判断任务人数是否达到上限
        Task task = taskMapper.getTaskById(taskId);
        if (task.getMemberCountMax() <= task.getTaskApplyCount()) {
            return new UniversalResponseBody<>(ResponseResultEnum.TASK_MEMBER_REACH_MAX.getCode(),ResponseResultEnum.TASK_MEMBER_REACH_MAX.getMsg());
        }

        // 判断是否在任务报名时间内
        if (!TimeUtil.isApplyTime(task.getTaskStartTime(), new Date())) {
            return new UniversalResponseBody<>(ResponseResultEnum.NOT_AT_APPLY_TIME.getCode(), ResponseResultEnum.NOT_AT_APPLY_TIME.getMsg());
        }

        // 将该用户加入到任务中
        TaskMember taskMember = new TaskMember();
        taskMember.setTaskId(taskId);
        taskMember.setUserId(userId);
        taskMember.setUserTaskStatus(UserTaskStatusEnum.USER_APPLIED.getMsg());
        taskMember.setUserPigeonEggCount(0);
        // 更新任务参加人数
        int affectedRow = taskMapper.addUserTask(taskMember);
        task.setTaskApplyCount(task.getTaskApplyCount() + 1);
        taskMapper.updateTaskApplyCount(task.getTaskId(), task.getTaskApplyCount());
        if (affectedRow > 0) {
            return new UniversalResponseBody<>(ResponseResultEnum.SUCCESS.getCode(),ResponseResultEnum.SUCCESS.getMsg(), task);
        }

        return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(),ResponseResultEnum.FAILED.getMsg());
    }

    @Override
    @Transactional
    public UniversalResponseBody<Task> taskCheckIn(Double checkLongitude, Double checkLatitude, Integer taskId, Integer userId) {
        // 判断用户是否签到过
        String status = taskMapper.getUserTaskStatus(userId, taskId);
        if (status.equals(UserTaskStatusEnum.USER_CHECK_IN.getMsg())) {
            return new UniversalResponseBody<>(ResponseResultEnum.CHECK_IN_ALREADY.getCode(), ResponseResultEnum.CHECK_IN_ALREADY.getMsg());
        }

        Task task = taskMapper.getTaskById(taskId);

        // 判断用户是否在签到时间范围内
        if (!TimeUtil.isCheckInTime(task.getTaskStartTime(), new Date())) {
            return new UniversalResponseBody<>(ResponseResultEnum.NOT_AT_CHECK_IN_TIME.getCode(), ResponseResultEnum.NOT_AT_CHECK_IN_TIME.getMsg());
        }

        // 判断用户签到是否在任务地点的50m范围内
        if (GeoDistUtil.getDistance(task.getCheckLongitude(), task.getCheckLatitude(), checkLongitude, checkLatitude) > 50){
            return new UniversalResponseBody<>(ResponseResultEnum.NOT_IN_CHECK_LOCATION.getCode(), ResponseResultEnum.NOT_IN_CHECK_LOCATION.getMsg());
        }

        // 更新打卡地点、打卡状态
        TaskMember taskMember = new TaskMember();
        taskMember.setTaskId(taskId);
        taskMember.setUserId(userId);
        taskMember.setCheckInLongitude(checkLongitude);
        taskMember.setCheckInLatitude(checkLatitude);
        taskMember.setUserTaskStatus(UserTaskStatusEnum.USER_CHECK_IN.getMsg());
        taskMember.setCheckInTime(new Date());

        int affectedRow = taskMapper.updateTaskMember(taskMember);
        if (affectedRow > 0) {
            return new UniversalResponseBody<>(ResponseResultEnum.SUCCESS.getCode(),ResponseResultEnum.SUCCESS.getMsg(), task);
        }

        return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(),ResponseResultEnum.FAILED.getMsg());
    }

    @Override
    public UniversalResponseBody<List<Task>> getTeamsTask(Integer teamId) {
        List<Task> teamTasks = taskMapper.getTeamsTask(teamId);
        if (teamTasks != null){
            return new UniversalResponseBody<>(ResponseResultEnum.SUCCESS.getCode(),ResponseResultEnum.SUCCESS.getMsg(), teamTasks);
        }
        return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(),ResponseResultEnum.FAILED.getMsg());
    }

    @Override
    public UniversalResponseBody<List<Task>> getTeamsTaskUnExp(Integer teamId) {
        List<Task> teamTasks = taskMapper.getTeamsTaskExcStatus(teamId, TaskStatusEnum.TASK_CREATED.getTaskStatus());
        if (teamTasks != null){
            return new UniversalResponseBody<>(ResponseResultEnum.SUCCESS.getCode(),ResponseResultEnum.SUCCESS.getMsg(), teamTasks);
        }
        return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(),ResponseResultEnum.FAILED.getMsg());
    }

    @Override
    public UniversalResponseBody<List<Task>> getUserTeamTasks(Integer userId, Integer teamId) {
        Integer teamIdForOne = teamMemberMapper.getUserTeamForOne(userId, teamId);
        List<Task> teamTasks = taskMapper.getTeamsTask(teamIdForOne);
        if (teamTasks != null){
            return new UniversalResponseBody<>(ResponseResultEnum.SUCCESS.getCode(),ResponseResultEnum.SUCCESS.getMsg(), teamTasks);
        }
        return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(),ResponseResultEnum.FAILED.getMsg());
    }

    @Override
    public UniversalResponseBody<List<Task>> getUserTeamTasksUnExp(Integer userId, Integer teamId) {
        Integer teamIdForOne = teamMemberMapper.getUserTeamForOne(userId, teamId);
        List<Task> teamTasks = taskMapper.getTeamsTaskByStatus(teamId, TaskStatusEnum.TASK_CREATED.getTaskStatus());
        if (teamTasks != null){
            return new UniversalResponseBody<>(ResponseResultEnum.SUCCESS.getCode(),ResponseResultEnum.SUCCESS.getMsg(), teamTasks);
        }
        return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(),ResponseResultEnum.FAILED.getMsg());
    }

    @Override
    public UniversalResponseBody<Task> taskCheckOut(Double checkLongitude, Double checkLatitude, Integer taskId, Integer userId) {
        // 判断用户是否已签退
        String status = taskMapper.getUserTaskStatus(userId, taskId);
        if (status.equals(UserTaskStatusEnum.USER_CHECK_OUT.getMsg())) {
            return new UniversalResponseBody<>(ResponseResultEnum.CHECK_OUT_ALREADY.getCode(), ResponseResultEnum.CHECK_OUT_ALREADY.getMsg());
        }

        Task task = taskMapper.getTaskById(taskId);

        // 判断用户是否在签退时间范围内
        if (!TimeUtil.isCheckOutTime(task.getTaskEndTime(), new Date())) {
            return new UniversalResponseBody<>(ResponseResultEnum.NOT_AT_CHECK_OUT_TIME.getCode(), ResponseResultEnum.NOT_AT_CHECK_OUT_TIME.getMsg());
        }

        // 判断用户是否在签退范围内
        if (GeoDistUtil.getDistance(task.getCheckLongitude(), task.getCheckLatitude(), checkLongitude, checkLatitude) > 50){
            return new UniversalResponseBody<>(ResponseResultEnum.NOT_IN_CHECK_LOCATION.getCode(), ResponseResultEnum.NOT_IN_CHECK_LOCATION.getMsg());
        }

        // 任务完成，新增用户鸽子蛋数量
        userMessageMapper.updatePigeonEggCount(userId, 1);

        // 更新打卡相关
        TaskMember taskMember = new TaskMember();
        taskMember.setTaskId(taskId);
        taskMember.setUserId(userId);
        taskMember.setCheckOutLongitude(checkLongitude);
        taskMember.setCheckOutLatitude(checkLatitude);
        taskMember.setUserTaskStatus(UserTaskStatusEnum.USER_CHECK_OUT.getMsg());
        taskMember.setUserPigeonEggCount(1);
        taskMember.setCheckOutTime(new Date());

        int affectedRow = taskMapper.updateTaskMember(taskMember);
        if (affectedRow > 0) {
            return new UniversalResponseBody<>(ResponseResultEnum.SUCCESS.getCode(),ResponseResultEnum.SUCCESS.getMsg(), task);
        }

        return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(),ResponseResultEnum.FAILED.getMsg());
    }
}
