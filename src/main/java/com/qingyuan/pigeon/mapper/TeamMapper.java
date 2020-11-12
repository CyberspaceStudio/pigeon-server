package com.qingyuan.pigeon.mapper;


import com.qingyuan.pigeon.pojo.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * 通过teamId来查找团队
     * @param teamId
     * @return
     */
    Team getTeamById(Integer teamId);

    /**
     * 更新团队申请人数
     * @param teamApplyCount
     * @return
     */
    int updateTeamApplyCount(@Param("teamId") Integer teamId, @Param("teamApplyCount") Integer teamApplyCount);
}
