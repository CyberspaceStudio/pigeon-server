package com.qingyuan.pigeon.controller;

import com.qingyuan.pigeon.pojo.PO.TokenPO;
import com.qingyuan.pigeon.pojo.User;
import com.qingyuan.pigeon.service.CheckInService;
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

    @Resource
    private CheckInService checkInService;

    /**
     * 用户注册
     * @param userTel
     * @param userPwd
     * @return
     * @apiNote 已上线
     */
    @PostMapping("/register")
    public UniversalResponseBody<TokenPO> userRegister(String userTel,String userPwd){
        return userService.userRegister(userTel, userPwd);
    }

    /**
     * 登录
     * @param userTel 手机号
     * @param userPwd 密码
     * @return
     * @apiNote 已上线
     */
    @PostMapping("/login")
    public UniversalResponseBody<TokenPO> userLogin(String userTel,String userPwd){
        return userService.userLogin(userTel,userPwd);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     * @apiNote 已上线,此接口不更新用户的userImageUrl的值,更新用户头像请调用更新用户头像的接口
     */
    @PostMapping("/message")
    public UniversalResponseBody<User> updateUserMessage(User user){
        return userService.updateUserMessage(user);
    }

    /**
     * 通过id获取用户信息
     * @param userId 用户id
     * @return
     * @apiNote 已上线
     */
    @GetMapping("/message/id")
    public UniversalResponseBody<User> getUserMessageById(Integer userId){
        return userService.getUserMessageById(userId);
    }

    /**
     * 通过手机号获取用户信息
     * @param userTel
     * @return
     * @apiNote 已上线
     */
    @GetMapping("/message/tel")
    public UniversalResponseBody<User> getUserMessageByTel(String userTel){
        return userService.getUserMessageByTel(userTel);
    }

    /**
     * 上传用户头像
     * @param multipartFile
     * @param userId
     * @return 用户头像路径
     * @apiNote 已上线,图片最大为5MB, 默认用户头像为https://minimalist.net.cn/image-pigeon/user-avatar/default.png
     */
    @PostMapping("/avatar")
    public UniversalResponseBody<String> updateUserAvatar(MultipartFile multipartFile,Integer userId){
        return userService.updateUserAvatar(multipartFile,userId);
    }


    /**
     * 发送验证码
     * @param userTel
     * @return
     * @apiNote 已上线
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
     * @apiNote 已上线
     */
    @PostMapping("/verifycode/check")
    public UniversalResponseBody checkVerificationCode(String userTel,String code) {
        return userService.checkVerificationCode(userTel, code);
    }


    /**
     * 用户每日签到
     * @param userId
     * @return
     * @apiNote 返回的是用户此次签到获得的鸽子蛋数量
     */
    @PostMapping("/check-in")
    public UniversalResponseBody<Integer> userCheckIn(Integer userId){
        return checkInService.userCheckIn(userId);
    }

    /**
     * 用户本月签到状况
     * @param userId
     * @return
     * @apiNote 此接口返回的data是boolean数组(下标从1开始，结束下标为当月的天数)
     */
    @GetMapping("/check-in/detail")
    public UniversalResponseBody<Boolean[]> userCheckInInfo(Integer userId){
        return checkInService.userCheckInInfo(userId);
    }
    
}
