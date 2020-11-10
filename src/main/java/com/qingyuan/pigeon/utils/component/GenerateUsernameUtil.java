package com.qingyuan.pigeon.utils.component;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Author: qyl
 * @Date: 2020/11/9 21:23
 */
@Component
public class GenerateUsernameUtil {

    private static final String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 生成用户名
     *
     * @return
     */
    public String generateUsername() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(62);
            sb.append(str.charAt(index));
        }
        return sb.toString();
    }
}
