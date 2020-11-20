package com.qingyuan.pigeon.utils.component;

import com.alibaba.fastjson.JSON;
import org.springframework.data.geo.Distance;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.awt.image.Kernel;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis操作工具类
 *
 * @program: uapply
 * @author: GuoShuSong
 * @create: 2020-05-19 14:22
 **/
@Component
public class RedisUtil{

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setValue(String key, String value) {
        boolean result = false;
        try {
            stringRedisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setValueByTime(String key, String value) {
        boolean result = false;
        try {
            stringRedisTemplate.opsForValue().set(key, value,5*60, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 更新缓存并设置过期时间(单位:秒)
     * @param key
     * @param value
     * @param times
     * @return
     */
    public boolean getAndSetByTime(String key, String value,Integer times) {
        boolean result = false;
        try {
            stringRedisTemplate.opsForValue().set(key, value,times, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新缓存
     */
    public boolean getAndSet(String key, String value) {
        boolean result = false;
        try {
            stringRedisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除缓存
     */
    public boolean delete(String key) {
        boolean result = false;
        try {
            stringRedisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 用户签到
     */
    public Boolean setBit(String key, long offset, boolean value) {
        return stringRedisTemplate.opsForValue().setBit(key, offset, value);
    }

    /**
     * 查看签到状态
     */
    public Boolean getBit(String key, int offset) {
        return stringRedisTemplate.opsForValue().getBit(key, offset);
    }

    /**
     * redis的bitField命令
     */
    public List<Long> bitField(String key, int limit, int offset) {
        return stringRedisTemplate.execute(
                (RedisCallback<List<Long>>) con->con.bitField(key.getBytes(),
                        BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.unsigned(limit)).valueAt(offset))
        );
    }

}

