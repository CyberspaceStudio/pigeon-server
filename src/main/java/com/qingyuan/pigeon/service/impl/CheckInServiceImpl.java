package com.qingyuan.pigeon.service.impl;

import com.qingyuan.pigeon.enums.ResponseResultEnum;
import com.qingyuan.pigeon.service.CheckInService;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import com.qingyuan.pigeon.utils.component.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Author: qyl
 * @Date: 2020/11/19 0:14
 */
@Service
public class CheckInServiceImpl implements CheckInService {

    @Resource
    private RedisUtil redisUtil;

    private static final String CHECK_IN_KEY = "user-daily-check-in-";

    @Override
    public UniversalResponseBody<Integer> userCheckIn(Integer userId) {
        String key = CHECK_IN_KEY + userId;
        LocalDate date = LocalDate.now();

        // 判断今日是否签到过
        Boolean result = redisUtil.getBit(key, date.getDayOfMonth());
        if (result != null && result) {
            return new UniversalResponseBody<>(ResponseResultEnum.DUPLICATE_CHECK_IN.getCode(), ResponseResultEnum.DUPLICATE_CHECK_IN.getMsg());
        }
        // 用户签到
        redisUtil.setBit(key, date.getDayOfMonth(), true);

        // 获取连续签到的天数
        int continuousDay = getContinuousDay(userId);
        // 签到获得的鸽子蛋奖励
        int pigeonEggs;
        switch(continuousDay)
        {
            case 3 :
            case 5 :
                pigeonEggs = 2;
                break;
            case 10 :
            case 15 :
                pigeonEggs = 3;
                break;
            case 20 :
            case 25 :
                pigeonEggs = 4;
                break;
            default :
                pigeonEggs = 1;
        }
        if (continuousDay == date.lengthOfMonth()) {
            pigeonEggs = 5;
        }

        //根据连续签到的天数计算对应的鸽子蛋的奖励
        return new UniversalResponseBody<>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), pigeonEggs);
    }

    private int getContinuousDay(Integer userId) {
        int count = 0;
        String key = CHECK_IN_KEY + userId;
        LocalDate localDate = LocalDate.now();

        List<Long> list = redisUtil.bitField(key, localDate.getDayOfMonth(), 1);
        if (list != null && list.size() > 0) {
            long v = list.get(0) == null ? 0 : list.get(0);
            for (int i = 0; i < localDate.getDayOfMonth(); i++) {
                if (v >> 1 << 1 == v) {
                    if (i > 0) break;
                } else {
                    count++;
                }
                v >>= 1;
            }
        }
        return count;
    }

    @Override
    public UniversalResponseBody<Boolean[]> userCheckInInfo(Integer userId) {
        LocalDate localDate = LocalDate.now();
        String key = CHECK_IN_KEY + userId;
        Boolean[] result = new Boolean[localDate.lengthOfMonth() + 1];

        List<Long> list = redisUtil.bitField(key, localDate.lengthOfMonth(), 1);
        if (list != null && list.size() > 0) {
            long v = list.get(0) == null ? 0 : list.get(0);
            for (int i = localDate.lengthOfMonth(); i > 0; i--) {
                LocalDate date = localDate.withDayOfMonth(i);
                result[Integer.parseInt(date.format(DateTimeFormatter.ofPattern("dd")))] =  (v >> 1 << 1 != v);
                v >>= 1;
            }
        }
        return new UniversalResponseBody<>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), result);
    }
}
