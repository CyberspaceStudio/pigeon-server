package com.qingyuan.pigeon.mapper;


import com.qingyuan.pigeon.enums.UserAuthorityEnum;
import com.qingyuan.pigeon.pojo.Team;
import com.qingyuan.pigeon.pojo.User;
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
    Team getTeamsByIdType(Integer teamId, String activityType);

    /**
     * 添加管理员
     * @param teamId
     * @param userId
     * @param userAuthority
     * @return
     * @apiNote 此接口在添加完成管理员后会将所有管理员的用户信息返回
     */
   int addTeamAdmin(Integer teamId, Integer userId, Integer userAuthority);

    /**
     * 跟新已有管理员信息
     * @param teamId
     * @param userId
     * @param userAuthorityId
     * @return
     * @apiNote 此接口在添加完成管理员后会将所有管理员的用户信息返回
     */
    int updateUserAuthorityId(Integer teamId, Integer userId, Integer userAuthorityId);

    /**
     * 根据用户ID和团队ID查找 userAuthority
     * @param teamId
     * @param userId
     * @return
     */
   Integer getUserAuthorityId(Integer teamId, Integer userId);

    /**
     * 返回管理员的ID
     * @return
     */
   List<Integer> getAdminIds();

}
