package com.qingyuan.pigeon.mapper;

import com.qingyuan.pigeon.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 对应数据库: user
 * 对应实体类:User
 * @author GuoShuSong
 */
@Mapper
public interface UserMessageMapper {

    /**
     * 创建用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 通过手机查询用户
     * @param userTel
     * @return
     */
    User getUserByTel(String userTel);


    /**
     * 根据id获取用户
     * @param userId
     * @return
     */
    User getUserById(Integer userId);


    /**
     * 更新用户头像路径
     * @param userImageUrl
     * @param userId
     * @return
     */
    int updateUserImageUrl(String userImageUrl,Integer userId);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUserMessage(User user);
}
