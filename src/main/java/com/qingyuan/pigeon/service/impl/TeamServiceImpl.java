package com.qingyuan.pigeon.service.impl;

import com.qingyuan.pigeon.enums.ResponseResultEnum;
import com.qingyuan.pigeon.mapper.TeamMapper;
import com.qingyuan.pigeon.mapper.UserMessageMapper;
import com.qingyuan.pigeon.pojo.Team;
import com.qingyuan.pigeon.pojo.User;
import com.qingyuan.pigeon.service.TeamService;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 团队相关接口
 * @author 24605
 */
@Service
@Slf4j
public class TeamServiceImpl  implements TeamService {

    /**
     * 存储团队头像的文件夹,团队头像命名请用teamId.png
     */
    private static final String TEAM_AVATAR_DIR_PATH = "/a-pigeon/image-pigeon/team-avatar/";
    /**
     * 团队头像的访问地址,直接在该URL后面加上teamId.png就可以
     */
    private static  final String USER_IMAGE_URL = "https://minimalist.net.cn/image-pigeon/team-avatar/";

    @Resource
    private TeamMapper teamMapper;
    @Resource
    private UserMessageMapper userMessageMapper;

    @Override
    public UniversalResponseBody<List<User>> getTeamMembers(Integer teamId) {
        try{
            List<Integer> userIds = teamMapper.getTeamUserIds(teamId);
            List<User> users = new LinkedList<>();
            for (Integer userId:
                    userIds ) {
                users.add(userMessageMapper.getUserById(userId));
            }
            return new UniversalResponseBody<>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), users);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
    }

    @Override
    public UniversalResponseBody<Team> createTeam(Team team, MultipartFile multipartFile) {
        int affectedRow = teamMapper.createTeam(team);
        if (affectedRow < 1) {
            return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
        }

        String teamFileName = team.getTeamId() + ".png";
        String filePath = TEAM_AVATAR_DIR_PATH + teamFileName;
        log.info("团队上传头像，路径为", filePath);

        try {
            multipartFile.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
        }

        String imageUrl = USER_IMAGE_URL + teamFileName;
        team.setTeamAvatarUrl(imageUrl);
        int i = teamMapper.updateTeamAvatar(team.getTeamId(), imageUrl);
        if (i > 0) {
            return new UniversalResponseBody<>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), team);
        }

        return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
    }
}
