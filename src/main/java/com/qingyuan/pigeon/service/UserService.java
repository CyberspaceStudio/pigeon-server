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
     * @param userTel 用户电话
     * @param userPwd 用户密码
     * @return
     */
    public UniversalResponseBody<TokenPO> userLogin(String userTel,String userPwd);
}
