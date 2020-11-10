package com.qingyuan.pigeon.controller;

import com.qingyuan.pigeon.pojo.PO.TokenPO;
import com.qingyuan.pigeon.pojo.User;
import com.qingyuan.pigeon.service.UserService;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 用户相关接口
 * @program: course
 * @author: GuoShuSong
 * @create: 2020-10-21 19:25
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    @Qualifier("userServiceImpl")
    private UserService userService;

    /**
     * 用户注册
     * @param userTel
     * @param userPwd
     * @return
     */
    @PostMapping("/register")
    public UniversalResponseBody<User> userRegister(String userTel,String userPwd){
        return null;
    }


    /**
     * 登录
     * @param userTel 手机号
     * @param userPwd 密码
     * @return
     */
    @PostMapping("/login")
    public UniversalResponseBody<TokenPO> userLogin(String userTel,String userPwd){
        return null;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PostMapping("/message")
    public UniversalResponseBody<User> updateUserMessage(User user){
        return null;
    }

    /**
     * 通过id获取用户信息
     * @param userId 用户id
     * @return
     */
    @GetMapping("/message")
    public UniversalResponseBody<User> getUserMessageById(Integer userId){
        return userService.getUserMessageById(userId);
    }

    /**
     * 更新用户头像
     * @param multipartFile
     * @param userId
     * @return 用户头像路径
     */
    @PostMapping("/avatar")
    public UniversalResponseBody<String> updateUserAvatar(MultipartFile multipartFile,Integer userId){
        return null;
    }


    /**
     * 发送验证码
     * @param userTel
     * @return
     */
    @PostMapping("/verifycode/send")
    public UniversalResponseBody sendVerificationCode(String userTel){
        return userService.sendVerificationCode(userTel);
    }

    /**
     * 校验验证码
     * @param userTel
     * @param code
     * @return
     */
    @PostMapping("/verifycode/check")
    public UniversalResponseBody checkVerificationCode(String userTel,String code) {
        return userService.checkVerificationCode(userTel, code);
    }
}
