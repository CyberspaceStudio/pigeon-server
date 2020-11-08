package com.qingyuan.pigeon.pojo;

import lombok.Data;

/**
 * @program: course
 * @author: GuoShuSong
 * @create: 2020-10-21 19:07
 **/
@Data
public class User {

    /**
     * 用户id(数据库自动递增生成)
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户性别
     */
    private String userSex;

    /**
     * 用户电话
     */
    private String userTel;

    /**
     * 用户头像
     */
    private String userImageUrl;


    /**
     * 用户鸽子蛋数量
     */
   private Integer pigeonEggCount;

}
