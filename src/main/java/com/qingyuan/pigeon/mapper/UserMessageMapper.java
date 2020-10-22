package com.qingyuan.pigeon.mapper;

import com.qingyuan.pigeon.pojo.User;

/**
 * 对应数据库: user_message
 * 对应实体类:User
 * @author GuoShuSong
 */
public interface UserMessageMapper {

    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    User getUserByUserId(Integer userId);

    /**
     * 根据openid获取用户信息
     * @param openid
     * @return
     */
    User getUserByopenid(String openid);

    /**
     * 插入用户
     * @param user
     * @return
     */
    int insertUser(User user);
}
