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
    @Qualifier("TaskServiceImpl")
    TaskService taskService;


    /**
     * 新增任务
     * @param task
     * @return
     * @apiNote taskId以及taskStatus不用填写
     */
    @PostMapping("/add")
    public UniversalResponseBody<Task> addTask(Task task){
        return taskService.addTask(task);
    }

    /**
     * 查询用户的所有任务
     * @param userId
     * @return
     */
    @GetMapping("/user")
    public UniversalResponseBody<List<Task>> getUserTasks(Integer userId){
        return null;
    }

    /**
     * 报名任务
     * @param userId
     * @param taskId
     * @return
     */
    @PostMapping("/apply")
    public UniversalResponseBody<Task> applyTask(Integer userId,Integer taskId){
        return null;
    }

    /**
     * 任务签到
     * @param checkLongitude 签到经度
     * @param checkLatitude 签到纬度
     * @param taskId 任务id
     * @param userId 用户id
     * @return
     */
    @PostMapping("/checkin")
    public UniversalResponseBody<Task> taskCheckIn(Double checkLongitude,Double checkLatitude,Integer taskId,Integer userId){
       return null;
    }
}
