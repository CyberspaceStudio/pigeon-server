package com.qingyuan.pigeon.mapper;


import com.qingyuan.pigeon.pojo.Team;
import org.apache.ibatis.annotations.Mapper;

/**
 * 对应数据库:team team_member
 * @author 24605
 */
@Mapper
public interface TeamMapper {

    int createTeam(Team team);

    int updateTeamAvatar(Integer teamId, String avatarUrl);
}
