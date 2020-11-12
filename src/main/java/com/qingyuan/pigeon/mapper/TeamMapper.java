package com.qingyuan.pigeon.mapper;


import com.qingyuan.pigeon.pojo.Team;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;

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
     * 通过teamId来获取团队
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

    /**
     * 通过 userId 查询所有团队信息
     * @param userId
     * @return
     */
    List<Integer> getTeamsByUserId(Integer userId);

    /**
     * 根据活动类型获取团队信息
     * @param teamId
     * @param activityType 详情见活动类型字典数据
     * @return
     */
    Team getTeamsByType(Integer teamId, String activityType);

}
