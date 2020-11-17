package com.qingyuan.pigeon.service;

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
}
