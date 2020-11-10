package com.qingyuan.pigeon.service.impl;

import com.qingyuan.pigeon.service.TeamService;
import org.springframework.stereotype.Service;

/**
 * 团队相关接口
 * @author 24605
 */
@Service
public class TeamServiceImpl  implements TeamService {

    /**
     * 存储团队头像的文件夹,团队头像命名请用teamId.png
     */
    private static final String TEAM_AVATAR_DIR_PATH = "/a-pigeon/image-pigeon/team-avatar/";
    /**
     * 团队头像的访问地址,直接在该URL后面加上teamId.png就可以
     */
    private static  final String USER_IMAGE_URL = "https://minimalist.net.cn/image-pigeon/team-avatar/";
}
