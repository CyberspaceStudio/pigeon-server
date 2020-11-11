package com.qingyuan.pigeon.mapper;


import com.qingyuan.pigeon.pojo.Team;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 对应数据库:team team_member
 * @author 24605
 */
@Mapper
public interface TeamMapper {

    /**
     * 新建团队
     * @param team
     * @return
     */
    int createTeam(Team team);

    /**
     * 更新团队头像的路径
     * @param teamId
     * @param avatarUrl
     * @return
     */
    int updateTeamAvatar(Integer teamId, String avatarUrl);

    /**
     * 获取团队成员
     * @param teamId
     * @return
     */
    List<Integer> getTeamUserIds(Integer teamId);
}
