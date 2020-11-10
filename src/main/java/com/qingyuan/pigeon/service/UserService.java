package com.qingyuan.pigeon.service;


import com.qingyuan.pigeon.pojo.PO.TokenPO;
import com.qingyuan.pigeon.pojo.User;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.security.NoSuchAlgorithmException;

/**
 * 用户相关接口
 * @author GuoShuSong
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param userTel 用户电话
     * @param userPwd 用户密码
     * @return
     */
    UniversalResponseBody<TokenPO> userLogin(String userTel, String userPwd);

    /**
     * 发送验证码
     *
     * @param userTel
     * @return
     */
    UniversalResponseBody<String> sendVerificationCode(String userTel);

    /**
     * 校验验证码
     *
     * @param userTel
     * @param code 用户传入的验证码
     * @return
     */
    UniversalResponseBody checkVerificationCode(String userTel, String code);

    /**
     * 用户注册
     *
     * @param userTel
     * @param userPwd
     * @return
     */
    UniversalResponseBody<TokenPO> userRegister(String userTel, String userPwd);

    /**
     * 根据id获取用户信息
     * @param userId
     * @return
     */
    UniversalResponseBody<User> getUserMessageById(Integer userId);

    /**
     * 根据手机号获取用户信息
     * @param userTel
     * @return
     */
    UniversalResponseBody<User> getUserMessageByTel(String userTel);

    /**
     * 更新用户头像
     * @param multipartFile
     * @param userId
     * @return
     */
    UniversalResponseBody<String> updateUserAvatar(MultipartFile multipartFile, Integer userId);

    /**
     * 更新用户信息
     * @param user
     * @return
     * @apiNote 此接口不更新用户的userImageUrl的值,更新用户头像请调用更新用户头像的接口
     */
    UniversalResponseBody<User> updateUserMessage(User user);
}
