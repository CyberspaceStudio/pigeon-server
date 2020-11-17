package com.qingyuan.pigeon.controller;

import com.qingyuan.pigeon.utils.UniversalResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: qyl
 * @Date: 2020/11/16 20:33
 */
@RequestMapping("/apk")
@RestController
public class ApkController {

    /**
     * 上传apk文件
     * @param multipartFile
     * @param version
     * @return /a-pigeon/apk
     */
    @PostMapping("/upload")
    public UniversalResponseBody<String> uploadApk(MultipartFile multipartFile,String version){
        return null;
    }

    /**
     * 获取最新版本号
     * @return
     * @apiNote 返回为false代表是最新版本 返回为true代表有最新版本
     */
    @GetMapping("/version")
    public UniversalResponseBody<Boolean> getVersionLatest(){
        return null;
    }

}
