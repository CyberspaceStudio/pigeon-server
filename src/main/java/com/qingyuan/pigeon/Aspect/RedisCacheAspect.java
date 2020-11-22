package com.qingyuan.pigeon.Aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qingyuan.pigeon.annoation.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: qyl
 * @Date: 2020/11/21 21:41
 */
@Aspect
@Component
@Slf4j
public class RedisCacheAspect {

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    /**
     * 设置注解的切入点
     */
    @Pointcut(value = "@annotation(com.qingyuan.pigeon.annoation.RedisCache)")
    public void RedisCachePointcut() {}

    /**
     * 采用环绕的方式
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("RedisCachePointcut()")
    public Object redisCache(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        RedisCache redisCache = method.getAnnotation(RedisCache.class);
        String prefix = redisCache.prefix();
        String key = redisCache.key();
        long expireTime = redisCache.expireTime();
        TimeUnit timeUnit = redisCache.unit();

        Object proceed;
        String methodName = method.getName();
        Object[] args = joinPoint.getArgs();

        //解析EL表达式
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression =expressionParser.parseExpression(key);

        //用来组合EL表达式和方法参数的EL上下文对象
        EvaluationContext evaluationContext = new StandardEvaluationContext();

        //获取方法的参数，将参数放入evaluationContext
        DefaultParameterNameDiscoverer defaultParameterNameDiscoverer = new DefaultParameterNameDiscoverer();
        String[] parameterNames = defaultParameterNameDiscoverer.getParameterNames(method);
        for (int i = 0; i < parameterNames.length; i++) {
            evaluationContext.setVariable(parameterNames[i],args[i].toString());
        }

        String cacheKey = getCacheableKey(expression.getValue(evaluationContext).toString(), prefix, args, methodName);

        String value = getValueInCache(cacheKey);
        if (value == null) {
            log.info("query from database");
            proceed = joinPoint.proceed();
            // 查询到的数据保存到redis中
            String result = JSONObject.toJSONString(proceed);
            setValueInCache(cacheKey, result, expireTime, timeUnit);
            return proceed;
        } else {
            log.info("query from redis");
            return getValueActualTypeData(method, value);
        }
    }

    /**
     * JSON格式与Class对象间的转换
     */
    private Object getValueActualTypeData(Method method, String value) throws ClassNotFoundException {
        Class<?> returnActualType = getReturnActualType(method);
        try {
            return JSONObject.parseArray(value, returnActualType);
        } catch (Exception e) {
            return JSONObject.parseObject(value, returnActualType);
        }
    }

    private Class<?> getReturnActualType(Method method) throws ClassNotFoundException {
        Type genericReturnType = method.getGenericReturnType();
        if (genericReturnType instanceof ParameterizedType) {
            Type[] actualTypes = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            for (Type actualType : actualTypes) {
                return Class.forName(actualType.getTypeName());
            }
        } else {
            return (Class<?>) genericReturnType;
        }
        return null;
    }

    private void setValueInCache(String cacheKey, String results, long expireTime, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(cacheKey, results, expireTime, timeUnit);
    }

    private String getValueInCache(String cacheKey) {
        return redisTemplate.opsForValue().get(cacheKey);
    }

    /**
     * 获取要缓存的key 默认值为查询条件的第一个参数 可以通过key属性指定key
     *
     * @param key       存储的key
     * @param prefix    存储前缀
     * @param args      方法入参参数
     * @param methodName 方法名称,当方法没有入参时且没有指定key默认使用方法名称作为key
     * @return
     */
    public String getCacheableKey(String key, String prefix, Object[] args, String methodName) {
        String cacheKey = "";
        if (StringUtils.isNotBlank(prefix)) {
            cacheKey = prefix + "-";
        }
        if (StringUtils.isNotBlank(key)) {
            return cacheKey += key;
        }
        if (args != null && args.length > 0) {
            return cacheKey += args[0];
        }
        //方法名称
        return methodName;
    }
}
