package com.qingyuan.pigeon.controller;

import com.qingyuan.pigeon.pojo.Team;
import com.qingyuan.pigeon.pojo.User;
import com.qingyuan.pigeon.service.TeamService;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;


/**
 * 团队相关接口
 * @program: pigeon_server
 * @author: GuoShuSong
 * @create: 2020-11-08 10:26
 **/
@RestController
@RequestMapping("/team")
public class TeamController {


    @Resource
    private TeamService teamService;

    /**
     * 新建团队
     * @param team
     * @return
     * @apiNote 此接口中teamImageUrl不用填写
     */
    @PostMapping("/create")
    public UniversalResponseBody<Team> createTeam(Team team,MultipartFile multipartFile){
        return teamService.createTeam(team, multipartFile);
    }

    /**
     * 加入团队
     * @param teamId
     * @param userId
     * @return
     */
    @PostMapping("/apply")
    public UniversalResponseBody<Team> applyTeam(Integer teamId,Integer userId){
        return null;
    }


    /**
     * 获取团队成员
     * @param teamId
     * @return
     */
    @GetMapping("/members")
    public UniversalResponseBody<List<User>> getTeamUsers(Integer teamId){
        return teamService.getTeamMembers(teamId);
    }


    /**
     * 添加管理员
     * @param teamId
     * @param userTel
     * @return
     * @apiNote 此接口在添加完成管理员后会将所有管理员的用户信息返回
     */
    @PostMapping("/admin")
    public UniversalResponseBody<List<User>> addTeamAdmin(Integer teamId,String userTel){
        return null;
    }

    /**
     * 更新团队头像
     * @param multipartFile
     * @param teamId
     * @return 团队头像路径
     */
    @PostMapping("/avatar")
    public UniversalResponseBody<String> updateUserAvatar(MultipartFile multipartFile, Integer teamId){
        return null;
    }


    /**
     * 根据活动类型获取团队信息
     * @param userId
     * @param activityType 详情见活动类型字典数据
     * @return
     */
    @GetMapping("/type")
    public UniversalResponseBody<List<Team>> getTeamsByType(Integer userId,String activityType){
        return null;
    }

    /**
     * 根据团队Id获取团队
     * @param teamId
     * @return
     */
    public UniversalResponseBody<Team> getTeamById(Integer teamId){
        return null;
    }

}
