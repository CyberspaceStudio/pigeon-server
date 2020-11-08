package com.qingyuan.pigeon.service.impl;

import com.alibaba.fastjson.JSON;
import com.qingyuan.pigeon.enums.ResponseResultEnum;
import com.qingyuan.pigeon.mapper.UserMessageMapper;
import com.qingyuan.pigeon.pojo.PO.TokenPO;
import com.qingyuan.pigeon.pojo.PO.VerifyCodePO;
import com.qingyuan.pigeon.service.UserService;
import com.qingyuan.pigeon.utils.component.MessageUtil;
import com.qingyuan.pigeon.utils.component.RedisUtil;
import com.qingyuan.pigeon.utils.component.TokenUtil;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.SimpleFormatter;

/**
 * 用户登录接口实现类
 * @program: course
 * @author: GuoShuSong
 * @create: 2020-10-22 10:39
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMessageMapper userMessageMapper;
    @Resource
    private TokenUtil tokenutil;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private MessageUtil messageUtil;

    @Override
    public UniversalResponseBody<TokenPO> userLogin(String userTel, String userPwd) {
        return null;
    }

    @Override
    public UniversalResponseBody<String> sendVerificationCode(String userTel) {
        // 通过一定规则生成otp验证码
        Random random = new Random();
        // 随机生成了五位数验证码(10000 ~ 99999)
        int randomInt = random.nextInt(899999);
        randomInt += 10000;
        String verifyCode  = String.valueOf(randomInt);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            VerifyCodePO verifyCodePO =  new VerifyCodePO(userTel,verifyCode,sdf.format(new Date()));
            //redis key的格式user-verifyCode-userTel
            if(!redisUtil.getAndSet("user-verifyCode-"+userTel, JSON.toJSONString(verifyCodePO))){
                return new UniversalResponseBody<String>(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
            }
            if(messageUtil.sendVerifyCode(userTel,verifyCode)){
                return new UniversalResponseBody<String>(ResponseResultEnum.SUCCESS.getCode(),ResponseResultEnum.SUCCESS.getMsg());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new UniversalResponseBody<String>(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
    }
}
