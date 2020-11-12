package com.qingyuan.pigeon.service;

import com.qingyuan.pigeon.pojo.Team;
import com.qingyuan.pigeon.pojo.User;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
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
    UniversalResponseBody<Team> createTeam(Team team, MultipartFile multipartFile);

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
}
