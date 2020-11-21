package com.qingyuan.pigeon.config;


import com.qingyuan.pigeon.enums.TaskStatusEnum;
import com.qingyuan.pigeon.job.OrderDelayDto;
import com.qingyuan.pigeon.mapper.TaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.DelayQueue;


/**
 *
 * @author han long yi
 * @create 2020-11-20 19:05
 */
@Component
@Slf4j
public class OrderDelayUtil {

    @Resource
    private TaskMapper taskMapper;

    private static DelayQueue<OrderDelayDto> queue = new DelayQueue<>();


    /**
     * 定时将过期任务状态改为已过期
     * @param taskId
     * @param taskEndTime
     */
    public void orderDelay(Integer taskId, Date taskEndTime){
        // 延时队列
        while (true) {
            try {
                OrderDelayDto take = queue.take();
                log.info("任务自动过期" + take.toString());
                taskMapper.updateTaskStatus(taskId, TaskStatusEnum.TASK_EXPIRED.getTaskStatus());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void AddTaskDelay(Integer taskId,Date taskEndTime){
        OrderDelayDto o = new OrderDelayDto();
        log.info("新增任务过期"+taskId +  "  " + taskEndTime);
        o.setTaskId(taskId);
        o.setTaskEndTime(taskEndTime);
        queue.add(o);
    }
}
