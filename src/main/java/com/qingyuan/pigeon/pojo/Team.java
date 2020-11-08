package com.qingyuan.pigeon.pojo;

import lombok.Data;

/**
 * 团队
 * @program: pigeon_server
 * @author: GuoShuSong
 * @create: 2020-10-22 14:57
 **/
@Data
public class Team {

    /**
     * 团队id(数据库自动递增生成)
     */
    private Integer teamId;

    /**
     * 团队名称
     */
    private String teamName;

    /**
     * 团队类型(详情见活动类型字典)
     */
    private String activityType;

    /**
     * 团队成员上限
     */
    private Integer memberCountMax;

    /**
     * 团队介绍
     */
    private String teamIntroduction;

    /**
     * 团队头像路径
     */
    private String  teamAvatarUrl;
}
