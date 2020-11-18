package com.qingyuan.pigeon.service;

import com.qingyuan.pigeon.utils.UniversalResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: qyl
 * @Date: 2020/11/17 10:43
 */
public interface ApkService {

    /**
     * 下载apk文件
     * @param request
     * @param response
     * @throws IOException
     */
    void download(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * 上传apk文件
     * @param multipartFile
     * @param version
     * @return
     */
    UniversalResponseBody<String> uploadApk(MultipartFile multipartFile, String version);

    /**
     * 获取最新的版本
     * @return
     */
    UniversalResponseBody<Boolean> getVersionLatest(String version);
}
