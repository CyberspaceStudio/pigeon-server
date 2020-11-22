package com.qingyuan.pigeon.service;

import com.qingyuan.pigeon.pojo.Team;
import com.qingyuan.pigeon.pojo.User;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 团队相关接口
 * @author 24605
 */
public interface TeamService {

    /**
     * 新建团队
     * @param team
     * @param multipartFile
     * @return
     */
    UniversalResponseBody<Team> createTeam(Team team, Integer userId, MultipartFile multipartFile);

    /**
     * 加入团队
     * @param teamId
     * @param userId
     * @return
     */
    UniversalResponseBody<Team> applyTeam(Integer teamId,Integer userId);

    /**
     * 获得团队所有成员
     * @param teamId
     * @return
     */
    UniversalResponseBody<List<User>> getTeamUsers(Integer teamId);

    /**
     * 根据活动类型获取团队信息
     * @param userId
     * @param activityType 详情见活动类型字典数据
     * @return
     */
    UniversalResponseBody<List<Team>> getTeamsByType(Integer userId,String activityType);

    /**
     * 根据TeamId获取团队
     * @param teamId
     * @return
     */
    UniversalResponseBody<Team> getTeamByTeamId(Integer teamId);

    /**
     * 查询用户的所有团队
     * @param userId
     * @return
     */
    UniversalResponseBody<List<Team>> getTeamsByUserId(Integer userId);

    /**
     * 添加管理员
     * @param teamId
     * @param userTel
     * @return
     * @apiNote 此接口在添加完成管理员后会将所有管理员的用户信息返回
     */
    UniversalResponseBody<List<User>> addTeamAdmin(Integer teamId,String userTel);

    UniversalResponseBody<User> deleteTeamMember(Integer userId, Integer teamId);
}
