package com.qingyuan.pigeon.controller;

import com.qingyuan.pigeon.pojo.Team;
import com.qingyuan.pigeon.pojo.User;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    /**
     * 新建团队
     * @param team
     * @return
     * @apiNote 团队头像路径==调用上传图片的接口后，得到服务器返回的路径
     */
    @PostMapping("/create")
    public UniversalResponseBody<Team> createTeam(Team team){
        return null;
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
        return null;
    }
}