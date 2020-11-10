package com.qingyuan.pigeon.service.impl;

import com.qingyuan.pigeon.enums.ResponseResultEnum;
import com.qingyuan.pigeon.mapper.UserMessageMapper;
import com.qingyuan.pigeon.pojo.PO.TokenPO;
import com.qingyuan.pigeon.pojo.User;
import com.qingyuan.pigeon.service.UserService;
import com.qingyuan.pigeon.utils.component.*;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Random;

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
    private TokenUtil tokenUtil;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private MessageUtil messageUtil;
    @Resource
    private PasswordEncodeUtil passwordEncodeUtil;
    @Resource
    private GenerateUsernameUtil generateUsernameUtil;

    private static final String USER_AVATAR_DIR_PATH = "/a-pigeon/image-pigeon/user-avatar/";
    private static  final String USER_IMAGE_URL = "https://minimalist.net.cn/image-pigeon/user-avatar/";
    private static  final String DEFAULT_USER_IMAGE_URL = "https://minimalist.net.cn/image-pigeon/user-avatar/default.png";

    @Override
    public UniversalResponseBody<TokenPO> userLogin(String userTel, String userPwd) {
        User userByTel = userMessageMapper.getUserByTel(userTel);
        try{
            // 输入的userPwd加密
            String userPwdEncode = passwordEncodeUtil.encodeByMD5(userPwd);
            System.out.println(userPwd + userTel);
            if (userByTel == null  || !userPwdEncode.equals(userByTel.getUserPwd())){
                return new UniversalResponseBody<>(ResponseResultEnum.USER_LOGIN_ERROR.getCode(),ResponseResultEnum.USER_LOGIN_ERROR.getMsg());
            }else{
                TokenPO tokenPO = new TokenPO(tokenUtil.tokenByUserId(userByTel.getUserId()),userByTel);
                return new UniversalResponseBody<>(ResponseResultEnum.SUCCESS.getCode(),ResponseResultEnum.SUCCESS.getMsg(),tokenPO);
            }
        }catch (Exception e){
            return  new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(),ResponseResultEnum.FAILED.getMsg());
        }
    }

    @Override
    public UniversalResponseBody<String> sendVerificationCode(String userTel) {
        // 通过一定规则生成otp验证码
        Random random = new Random();
        // 随机生成了六位数验证码(100000 ~ 999999)
        int randomInt = random.nextInt(899999);
        randomInt += 100000;
        String verifyCode  = String.valueOf(randomInt);
        try{
            if(!redisUtil.getAndSetByTime("user-verifyCode-"+userTel, verifyCode,5*60)){
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

    @Override
    public UniversalResponseBody checkVerificationCode(String userTel, String code) {
        // 通过手机号获取验证码
        String verifyCode = redisUtil.getValue("user-verifyCode-" + userTel);

        // 校验验证码
        if (verifyCode!=null &&verifyCode.equals(code)) {
            return new UniversalResponseBody(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg());
        }

        return new UniversalResponseBody(ResponseResultEnum.VERITY_CODE_EXPIRED_OR_INCORRECT.getCode(), ResponseResultEnum.VERITY_CODE_EXPIRED_OR_INCORRECT.getMsg());
    }

    @Override
    @Transactional
    public UniversalResponseBody<TokenPO> userRegister(String userTel, String userPwd) {
        User userByTel = userMessageMapper.getUserByTel(userTel);
        if (userByTel != null) {
            return new UniversalResponseBody<>(ResponseResultEnum.USER_IS_EXISTED.getCode(), ResponseResultEnum.USER_IS_EXISTED.getMsg());
        }

        User user = new User();
        user.setUserTel(userTel);
        user.setPigeonEggCount(0);
        user.setUserImageUrl(DEFAULT_USER_IMAGE_URL);
        user.setUserName("pigeon_" + generateUsernameUtil.generateUsername());
        try {
            //加密密码
            user.setUserPwd(passwordEncodeUtil.encodeByMD5(userPwd));
        } catch (Exception e) {
            return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
        }
        //插入用户
        int affectedRow = userMessageMapper.insertUser(user);
        if (affectedRow > 0) {
            //生成token,并和user包装成TokenPO对象
            TokenPO tokenPO = new TokenPO(tokenUtil.tokenByUserId(user.getUserId()), user);
            return new UniversalResponseBody<>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), tokenPO);
        }
        return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
    }

    @Override
    public UniversalResponseBody<User> getUserMessageById(Integer userId) {
        User user = userMessageMapper.getUserById(userId);
        if (user != null){
            return new UniversalResponseBody<User>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(),user);
        }else{
            return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(),ResponseResultEnum.FAILED.getMsg());
        }
    }

    @Override
    public UniversalResponseBody<User> getUserMessageByTel(String userTel) {
        User user = userMessageMapper.getUserByTel(userTel);
        if (user != null){
            return new UniversalResponseBody<User>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(),user);
        }else{
            return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(),ResponseResultEnum.FAILED.getMsg());
        }
    }

    @Override
    public UniversalResponseBody<String> updateUserAvatar(MultipartFile multipartFile, Integer userId) {
        String newFileName = userId + ".png";
        String filePath = USER_AVATAR_DIR_PATH +newFileName;
        log.info("用户上传头像,路径为"+ filePath);
        try{
            multipartFile.transferTo(new File(filePath));
        }catch (Exception e){
            e.printStackTrace();
            return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(),ResponseResultEnum.FAILED.getMsg());
        }
        String imageUrl = USER_IMAGE_URL + newFileName;
        userMessageMapper.updateUserImageUrl(imageUrl,userId);
        log.info("用户上传头像，访问路径为" + imageUrl);
        return new UniversalResponseBody<String>(ResponseResultEnum.SUCCESS.getCode(),ResponseResultEnum.SUCCESS.getMsg(),imageUrl);
    }

    @Override
    public UniversalResponseBody<User> updateUserMessage(User user) {
        if(user.getUserId() ==null){
            log.error("更新用户信息时,userId为空"+ user.toString());
            return new UniversalResponseBody(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
        }
        int result = userMessageMapper.updateUserMessage(user);
        try {
            if (result>=0) {
                return new UniversalResponseBody(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new UniversalResponseBody(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
    }
}
