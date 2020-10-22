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
     * 团队id
     */
    private Integer teamId;

    /**
     * 团队名称
     */
    private String teamName;

    /**
     * 团队类型
     */
    private String teamType;

    /**
     * 团队人数下限
     */
    private Integer teamCountMin;


    /**
     * 团队成员上限
     */
    private Integer teamCountMax;

    /**
     * 团队介绍
     */
    private String teamIntroduction;

    /**
     * 团队头像路径
     */
    private String  teamAvatarUrl;
}
