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
     * @param code
     * @return
     */
    public UniversalResponseBody<TokenPO> userLogin(String code);
}
