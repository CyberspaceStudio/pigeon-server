package com.qingyuan.pigeon.controller;

import com.qingyuan.pigeon.pojo.Team;
import com.qingyuan.pigeon.pojo.User;
import com.qingyuan.pigeon.service.TeamService;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import org.springframework.web.bind.annotation.*;
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
     * @param userId
     * @param multipartFile
     * @return
     * @apiNote 已上线,此接口中teamImageUrl不用填写
     */
    @PostMapping("/create")
    public UniversalResponseBody<Team> createTeam(@PathVariable Team team, Integer userId, MultipartFile multipartFile){
        return teamService.createTeam(team, userId, multipartFile);
    }

    /**
     * 加入团队
     * @param teamId
     * @param userId
     * @return 已上线
     */
    @PostMapping("/apply")
    public UniversalResponseBody<Team> applyTeam(Integer teamId,Integer userId){
        return teamService.applyTeam(teamId, userId);
    }


    /**
     * 获取团队成员
     * @param teamId
     * @return 已上线
     */
    @GetMapping("/members")
    public UniversalResponseBody<List<User>> getTeamUsers(Integer teamId){
        return teamService.getTeamUsers(teamId);
    }


    /**
     * 添加管理员
     * @param teamId
     * @param userTel
     * @return
     * @apiNote 已上线 此接口在添加完成管理员后会将所有管理员的用户信息返回
     */
    @PostMapping("/admin")
    public UniversalResponseBody<List<User>> addTeamAdmin(Integer teamId,String userTel){
        return teamService.addTeamAdmin(teamId, userTel);
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
     * @return 已上线
     */
    @GetMapping("/type")
    public UniversalResponseBody<List<Team>> getTeamsByType(Integer userId,String activityType){
        return teamService.getTeamsByType(userId, activityType);
    }


    /**
     * 根据TeamId获取团队
     * @param teamId
     * @return 已上线 返回结果 成功(保证返回数据不为空) 失败
     */
    @GetMapping("/id")
    public UniversalResponseBody<Team> getTeamById(Integer teamId){
        return teamService.getTeamByTeamId(teamId);
    }

    /**
     * 查询用户的所有团队
     * @param userId
     * @return
     * @apiNote 已上线 返回结果 未参加任何团队 成功
     */
    @GetMapping("/user/id")
    public UniversalResponseBody<List<Team>> getTeamsByUserId(Integer userId){
        return teamService.getTeamsByUserId(userId);
    }


    /**
     * 删除团队成员
     * @param userId
     * @param teamId
     * @return
     * @apiNote 成员退出团队和管理员删除队员调用此接口
     */
   @DeleteMapping("/user/id")
    public UniversalResponseBody<User> deleteTeamMember(Integer userId,Integer teamId){
        return null;
   }
}
