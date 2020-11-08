package com.qingyuan.pigeon.service;


import com.qingyuan.pigeon.pojo.PO.TokenPO;
import com.qingyuan.pigeon.utils.UniversalResponseBody;

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
}
