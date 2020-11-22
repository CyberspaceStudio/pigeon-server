package com.qingyuan.pigeon.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 自定义缓存注解
 *
 * @Author: qyl
 * @Date: 2020/11/21 20:16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {

    /**
     * key的前缀
     */
    String prefix() default "";

    /**
     * key
     */
    String key();

    /**
     * 过期时间
     */
    long expireTime() default 60;

    /**
     * 时间单位
     */
    TimeUnit unit() default TimeUnit.SECONDS;
}
