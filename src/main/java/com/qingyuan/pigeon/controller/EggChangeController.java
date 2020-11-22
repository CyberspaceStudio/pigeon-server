package com.qingyuan.pigeon.controller;

import com.qingyuan.pigeon.pojo.PigeonEggChange;
import com.qingyuan.pigeon.service.EggChangeService;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 文件描述
 *
 * @author dingguo.an
 * @date 2020年11月21日 19:24
 */
@RestController
@RequestMapping("/egg")
public class EggChangeController {

    @Resource
    private EggChangeService eggChangeService;

    /**
     * 根据ID查询对象并返回
     * @param userId
     * @return
     */
    @GetMapping("/egg")
    public UniversalResponseBody<PigeonEggChange> getEggChangeById(Integer userId){
        return eggChangeService.getEggChangeById(userId);
    }

    /**
     * 向 pigeon_egg_change中添加数据
     * @param userId
     * @param changeCount
     * @param pigeonEggSource
     * @param changeTime
     * @return
     */
    @PostMapping("/add")
    public UniversalResponseBody<PigeonEggChange> addEggChange(Integer userId, Integer changeCount, String pigeonEggSource,
                                                                   Date changeTime){
        return eggChangeService.addEggChange(userId,changeCount,pigeonEggSource,changeTime);
    }
}
