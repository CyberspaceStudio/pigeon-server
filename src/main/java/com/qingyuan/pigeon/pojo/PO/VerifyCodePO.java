package com.qingyuan.pigeon.pojo.PO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @program: pigeon_server
 * @author: GuoShuSong
 * @create: 2020-11-08 17:18
 **/
@Data
@AllArgsConstructor
public class VerifyCodePO {

    /**
     * 用户电话
     */
    private String userTel;

    /**
     * 验证码
     */
    private String verifyCode;

    /**
     * 创建时间
     */
    private String createTime;
}
