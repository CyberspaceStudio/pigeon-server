package com.qingyuan.pigeon.mapper;

import com.qingyuan.pigeon.pojo.PigeonEggChange;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * 文件描述
 *
 * @author dingguo.an
 * @date 2020年11月21日 19:15
 */
@Mapper
public interface EggChangeMapper {
    /**
     * 根据ID查询对象并返回
     * @param userId
     * @return
     */
    PigeonEggChange getEggChangeById(Integer userId);

    /**
     * 向 pigeon_egg_change中添加数据并返回主键自增
     * @param userId
     * @param changeCount
     * @param pigeonEggSource
     * @param changeTime
     * @return
     */
    int addEggChange(Integer userId, Integer changeCount, String pigeonEggSource, Date changeTime);

}
