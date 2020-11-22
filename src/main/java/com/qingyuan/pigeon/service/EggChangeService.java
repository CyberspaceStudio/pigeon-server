package com.qingyuan.pigeon.service;

import com.qingyuan.pigeon.pojo.PigeonEggChange;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * 文件描述
 *
 * @author dingguo.an
 * @date 2020年11月21日 19:19
 */
public interface EggChangeService {
    /**
     * 根据 ID查询对象并返回
     * @param userId
     * @return
     */
    UniversalResponseBody<PigeonEggChange> getEggChangeById(Integer userId);

    /**
     * 向 pigeon_egg_change中添加数据
     * @param userId
     * @param changeCount
     * @param pigeonEggSource
     * @param changeTime
     * @return
     */
    UniversalResponseBody<PigeonEggChange> addEggChange(Integer userId, Integer changeCount, String pigeonEggSource,
                                                            Date changeTime);
}
