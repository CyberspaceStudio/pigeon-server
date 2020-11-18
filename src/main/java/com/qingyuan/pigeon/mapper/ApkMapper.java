package com.qingyuan.pigeon.mapper;

import com.qingyuan.pigeon.pojo.Apk;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: qyl
 * @Date: 2020/11/17 11:06
 */
@Mapper
public interface ApkMapper {

    /**
     * 获取apk的版本号
     * @return
     */
    String getVersion();

    /**
     * 插入apk信息
     * @param apk
     * @return
     */
    int insertApk(Apk apk);
}
