package com.qingyuan.pigeon.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: qyl
 * @Date: 2020/11/11 21:46
 */
@Mapper
public interface TeamMemberMapper {

    /**
     * 在团队中新增成员
     * @param teamId
     * @param userId
     * @return
     */
    int addUser(@Param("teamId") Integer teamId, @Param("userId") Integer userId);


    /**
     * 获得团队中的用户个数
     * @param teamId
     * @param userId
     * @return
     */
    int getUserNumberFromTeam(@Param("teamId") Integer teamId, @Param("userId") Integer userId);
}
