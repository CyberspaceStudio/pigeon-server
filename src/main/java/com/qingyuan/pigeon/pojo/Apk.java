package com.qingyuan.pigeon.pojo;

import lombok.Data;

/**
 * @Author: qyl
 * @Date: 2020/11/16 20:34
 */
@Data
public class Apk {

    /**
     * 版本号
     */
    private String version;

    /**
     * 版本存储路径
     * @ignore
     */
    private String path;
}
