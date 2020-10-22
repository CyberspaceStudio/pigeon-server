package com.qingyuan.pigeon.controller;

import com.qingyuan.pigeon.pojo.PO.TokenPO;
import com.qingyuan.pigeon.pojo.User;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户相关接口
 * @program: course
 * @author: GuoShuSong
 * @create: 2020-10-21 19:25
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 登录
     * @param code
     * @return
     */
    @PostMapping("/login")
    public UniversalResponseBody<TokenPO> userLogin(String code){
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
     * 获取用户信息
     * @param userId
     * @return
     */
    @GetMapping("/message")
    public UniversalResponseBody<User> getUserMessage(Integer userId){
        return null;
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
}
