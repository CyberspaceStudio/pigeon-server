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

    UniversalResponseBody<Team> createTeam(Team team, MultipartFile multipartFile);

    /**
     * 获取团队成员
     * @param teamId
     * @return
     */
    UniversalResponseBody<List<User>> getTeamMembers(Integer teamId);
}
