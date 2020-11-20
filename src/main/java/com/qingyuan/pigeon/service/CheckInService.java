package com.qingyuan.pigeon.service;

import com.qingyuan.pigeon.utils.UniversalResponseBody;

/**
 * @Author: qyl
 * @Date: 2020/11/19 0:14
 */
public interface CheckInService {

    /**
     * 用户签到
     * @param userId
     * @return 用户此次签到所获得的鸽子蛋数量
     */
    UniversalResponseBody<Integer> userCheckIn(Integer userId);

    /**
     * 获取用户的签到信息
     * @param userId
     * @return
     */
    UniversalResponseBody<Boolean[]> userCheckInInfo(Integer userId);
}
