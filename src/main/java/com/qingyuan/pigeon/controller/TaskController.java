package com.qingyuan.pigeon.controller;

import com.qingyuan.pigeon.pojo.Task;
import com.qingyuan.pigeon.service.TaskService;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 任务相关接口
 * @program: pigeon_server
 * @author: GuoShuSong
 * @create: 2020-11-08 10:25
 **/
@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource
    @Qualifier("taskServiceImpl")
    TaskService taskService;


    /**
     * 新增任务
     * @param task
     * @return
     * @apiNote 已上线 taskId以及taskStatus不用填写
     */
    @PostMapping("/add")
    public UniversalResponseBody<Task> addTask(Task task){
        return taskService.addTask(task);
    }

    /**
     * 查询用户的所有任务
     * @param userId
     * @return 已上线
     */
    @GetMapping("/user")
    public UniversalResponseBody<List<Task>> getUserTasks(Integer userId){
        return taskService.getUserTasks(userId);
    }

    /**
     * 报名任务
     * @param userId
     * @param taskId
     * @return 已上线
     */
    @PostMapping("/apply")
    public UniversalResponseBody<Task> applyTask(Integer userId,Integer taskId){
        return taskService.applyTask(userId, taskId);
    }

    /**
     * 任务签到
     * @param checkLongitude 签到经度
     * @param checkLatitude 签到纬度
     * @param taskId 任务id
     * @param userId 用户id
     * @return 已上线
     */
    @PostMapping("/checkin")
    public UniversalResponseBody<Task> taskCheckIn(Double checkLongitude,Double checkLatitude,Integer taskId,Integer userId){
       return taskService.taskCheckIn(checkLongitude, checkLatitude, taskId, userId);
    }

    /**
     * 获取用户在该团队的所有有效任务
     * @param userId
     * @param teamId
     * @return
     * @apiNote 除去已过期任务
     */
    @GetMapping("/team-user/unexpired")
    public UniversalResponseBody<List<Task>> getUserTeamTasksUnExp(Integer userId,Integer teamId){
        return taskService.getUserTeamTasksUnExp(userId, teamId);
    }

    /**
     * 获取团队的所有有效任务
     * @param teamId
     * @return
     * @apiNote 除去已过期任务
     */
    @GetMapping("/team/unexpired")
    public UniversalResponseBody<List<Task>> getTeamsTaskUnExp(Integer teamId){
        return taskService.getTeamsTaskUnExp(teamId);
    }

    /**
     * 获取用户在该团队的所有任务
     * @param userId
     * @param teamId
     * @return
     */
    @GetMapping("/team-user")
    public UniversalResponseBody<List<Task>> getUserTeamTasks(Integer userId,Integer teamId){
        return taskService.getUserTeamTasks(userId, teamId);
    }

    /**
     * 获取团队的所有任务
     * @param teamId
     * @return
     */
    @GetMapping("/team")
    public UniversalResponseBody<List<Task>> getTeamsTask(Integer teamId){
        return taskService.getTeamsTask(teamId);
    }
}
