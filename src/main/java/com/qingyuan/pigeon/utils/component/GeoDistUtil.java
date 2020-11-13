package com.qingyuan.pigeon.utils.component;

import org.springframework.stereotype.Component;

/**
 * @Author: qyl
 * @Date: 2020/11/12 20:23
 */
@Component
public class GeoDistUtil {

    // 地球平均半径6371.393km
    // 千米误差在0.5m内，万米误差在2m内
    private static final double EARTH_RADIUS = 6371393;

    public static double getDistance(Double taskLongitude, Double taskLatitude, Double userLongitude, Double userLatitude) {
        // 将经纬度转换成角度
        double taskLon = Math.toRadians(taskLongitude);
        double taskLan = Math.toRadians(taskLatitude);

        double userLon = Math.toRadians(userLongitude);
        double userLan = Math.toRadians(userLatitude);

        // 计算距离
        double distance = Math.acos(Math.sin(taskLan) * Math.sin(userLan) + Math.cos(taskLan) * Math.cos(userLan) * Math.cos(userLon - taskLon)) * EARTH_RADIUS;
        return distance;
    }

}
