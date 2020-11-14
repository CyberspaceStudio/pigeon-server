package com.qingyuan.pigeon.mapper;

import com.qingyuan.pigeon.pojo.Task;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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

    /**
     * 更新成员权限
     * @param userId
     * @param auth
     * @return
     */
    int updateUserAuthority(@Param("userId") Integer userId, @Param("auth") Integer auth);

    /**
     * 获取用户所在团队
     * @param userId
     * @return
     */
    List<Integer> getUserTeam(Integer userId);

    /**
     * 获取用户指定所在团队
     * @param userId
     * @return
     */
    Integer getUserTeamForOne(Integer userId, Integer teamId);
}
