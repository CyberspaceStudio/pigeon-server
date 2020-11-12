package com.qingyuan.pigeon.service.impl;

import com.qingyuan.pigeon.enums.ResponseResultEnum;
import com.qingyuan.pigeon.mapper.TaskMapper;
import com.qingyuan.pigeon.pojo.Task;
import com.qingyuan.pigeon.service.TaskService;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 任务相关接口
 * @author 24605
 */
@Service
public class TaskServiceImpl  implements TaskService {

    @Resource
    private TaskMapper taskMapper;

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
}
