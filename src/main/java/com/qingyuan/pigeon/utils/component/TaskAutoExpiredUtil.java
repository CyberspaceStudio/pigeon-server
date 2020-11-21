package com.qingyuan.pigeon.utils.component;

import com.qingyuan.pigeon.enums.TaskStatusEnum;
import com.qingyuan.pigeon.mapper.TaskMapper;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 任务自动过期类
 * @program: pigeon_server
 * @author: GuoShuSong
 * @create: 2020-11-21 11:20
 **/
@Component
public class TaskAutoExpiredUtil extends KeyExpirationEventMessageListener {

    @Resource
    private TaskMapper taskMapper;
    private static final String TASK_REDIS_PREFIX = "task-";

    public TaskAutoExpiredUtil(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        //如果过期的key包含 task-
        if (expiredKey.startsWith(TASK_REDIS_PREFIX)){
            //task- 从下标为5的地方开始取taskId
            Integer integer = Integer.parseInt(expiredKey.substring(5));
            //更新taskId对应的状态
            taskMapper.updateTaskStatus(integer, TaskStatusEnum.TASK_EXPIRED.getTaskStatus());
        }
    }
}
