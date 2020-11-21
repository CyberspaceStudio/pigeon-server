package com.qingyuan.pigeon.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 鸽子蛋来源
 * @program: pigeon_server
 * @author: GuoShuSong
 * @create: 2020-11-18 20:14
 **/
@Data
public class PigeonEggSource {

    /**
     * 用户id
     */
    private Integer UserId;

    /**
     * 鸽子蛋来源
     */
    private String pigeonEggSource;

    /**
     * 鸽子蛋变化数量
     */
    private Integer changeCount;

    /**
     * 变化时间
     */
    @JsonFormat(locale = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date changeTime;

}
