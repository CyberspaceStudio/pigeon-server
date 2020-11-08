package com.qingyuan.pigeon.enums;

/**
 * 用户权限枚举类
 * @author 24605
 */
public enum UserAuthorityEnum {
    SUPPER_ADMIN(0,"超级管理员"),
    TEAM_LEADER(1,"队长"),
    TEAM_ADMIN(2,"管理员"),
    TEAM_MEMBER(3,"团队成员");

    /**
     * 用户权限id
     */
    private Integer userAuthorityId;


    /**
     * 用户权限
     */
    private String userAuthority;


    UserAuthorityEnum(Integer userAuthorityId, String userAuthority) {
        this.userAuthorityId= userAuthorityId;
        this.userAuthority = userAuthority;
    }


    public Integer getUserAuthorityId() {
        return userAuthorityId;
    }

    public void setUserAuthorityId(Integer userAuthorityId) {
        this.userAuthorityId = userAuthorityId;
    }

    public String getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(String userAuthority) {
        this.userAuthority = userAuthority;
    }
}

