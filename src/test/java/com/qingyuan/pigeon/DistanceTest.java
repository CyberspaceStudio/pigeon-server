package com.qingyuan.pigeon;

import com.qingyuan.pigeon.utils.component.GeoDistUtil;
import org.junit.jupiter.api.Test;

/**
 * @Author: qyl
 * @Date: 2020/11/13 0:16
 */
public class DistanceTest {

    @Test
    void distanceTest() {
        double distance = GeoDistUtil.getDistance(100.0000, 30.0000, 100.1000, 30.1000);
        System.out.println(distance);
    }
}
