package com.qingyuan.pigeon.job;

import lombok.Data;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 任务类
 * @author han long yi
 * @create 2020-11-20 16:14
 */
@Data
public class OrderDelayDto implements Delayed {
    /**
     * 任务编号
     */
    private Integer taskId;
    /**
     * 过期时间
     */
    private Date taskEndTime;
    /**
     * 判断过期条件：过期时间大于等于当前时间就算过期
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.taskEndTime.getTime() - System.currentTimeMillis(), TimeUnit.NANOSECONDS);
    }
    /**
     * 订单加入队列的排序规则：最先过期的排在前面
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        OrderDelayDto orderDelayDto  = (OrderDelayDto) o;
        long time =orderDelayDto.getTaskEndTime().getTime();
        long time1 = this.taskEndTime.getTime();
        return  time == time1 ? 0 : time < time1 ? 1 : -1;
    }
}
