package com.qingyuan.pigeon.service.impl;

import com.qingyuan.pigeon.enums.ResponseResultEnum;
import com.qingyuan.pigeon.mapper.EggChangeMapper;
import com.qingyuan.pigeon.pojo.PigeonEggChange;
import com.qingyuan.pigeon.service.EggChangeService;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 文件描述
 *
 * @author dingguo.an
 * @date 2020年11月21日 19:20
 */
@Service
public class EggChangeImpl implements EggChangeService {
    @Resource
    private EggChangeMapper eggChangeMapper;

    @Override
    public UniversalResponseBody<PigeonEggChange> getEggChangeById(Integer userId) {
        PigeonEggChange pigeonEggChange = eggChangeMapper.getEggChangeById(userId);
        if (pigeonEggChange != null){
            return new UniversalResponseBody<>(ResponseResultEnum.SUCCESS.getCode(),ResponseResultEnum.SUCCESS.getMsg(),pigeonEggChange);
        }
        return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(),ResponseResultEnum.FAILED.getMsg(),pigeonEggChange);
    }

    @Override
    public UniversalResponseBody<PigeonEggChange> addEggChange(Integer userId, Integer changeCount, String pigeonEggSource,
                                                                 Date changeTime) {
        Integer result = eggChangeMapper.addEggChange(userId,changeCount,pigeonEggSource,changeTime);
        if (result > 0){
            return new UniversalResponseBody<>(ResponseResultEnum.SUCCESS.getCode(),ResponseResultEnum.SUCCESS.getMsg());
        }
        return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(),ResponseResultEnum.FAILED.getMsg());
    }
}
