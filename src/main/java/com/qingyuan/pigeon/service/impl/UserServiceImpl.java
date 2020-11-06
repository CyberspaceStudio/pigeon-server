package com.qingyuan.pigeon.service.impl;

import com.qingyuan.pigeon.mapper.UserMessageMapper;
import com.qingyuan.pigeon.pojo.PO.TokenPO;
import com.qingyuan.pigeon.service.UserService;
import com.qingyuan.pigeon.utils.TokenUtil;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Override
    public UniversalResponseBody<TokenPO> userLogin(String userTel, String userPwd) {
        return null;
    }
}
