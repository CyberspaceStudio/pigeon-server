package com.qingyuan.pigeon.service;

import com.qingyuan.pigeon.pojo.Team;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
}
