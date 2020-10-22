package com.qingyuan.pigeon.enums;

/**
 * 用户权限枚举类
 * @author 24605
 */
public enum UserAuthorityEnum {
    ADMIN(0,"管理员"),
    TEACHER(1,"教师"),
    STUDENT(2,"学生");

    /**
     * 用户权限id
     */
    private Integer UserAuthorityId;


    private String UserAuthority;


    UserAuthorityEnum(Integer UserAuthorityId, String UserAuthority) {
        UserAuthorityId = UserAuthorityId;
        UserAuthority = UserAuthority;
    }
}
