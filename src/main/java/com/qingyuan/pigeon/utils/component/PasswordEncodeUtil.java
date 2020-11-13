package com.qingyuan.pigeon.utils.component;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: qyl
 * @Date: 2020/11/9 19:46
 */
@Component
public class PasswordEncodeUtil {

    /**
     * 密码加密
     *
     * @param userPwd
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encodeByMD5(String userPwd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        // 加密密码
        String passwordDigest = MD5Encoder.encode(md5.digest(userPwd.getBytes("utf-8")));
        return passwordDigest;
    }
}
