package com.qingyuan.pigeon.service.impl;

import com.qingyuan.pigeon.enums.ResponseResultEnum;
import com.qingyuan.pigeon.mapper.ApkMapper;
import com.qingyuan.pigeon.pojo.Apk;
import com.qingyuan.pigeon.service.ApkService;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author: qyl
 * @Date: 2020/11/17 10:45
 */
@Service
@Slf4j
public class ApkServiceImpl implements ApkService {

    @Resource
    private ApkMapper apkMapper;

    private static final String APK_DIR_PATH = "/a-pigeon/apk/";

    @Override
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 获取版本号
        String version = apkMapper.getVersion();
        String fileName = "pigeon-" + version + ".apk";
        File file = new File(APK_DIR_PATH, fileName);

        if (file.exists()) {
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition","attachment;filename="+fileName);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;

            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();

                int i = bis.read(buffer);
                while(i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }  finally {
                if (fis != null) {
                    fis.close();
                }
                if (bis != null) {
                    bis.close();
                }
            }
        }
    }

    @Override
    public UniversalResponseBody<String> uploadApk(MultipartFile multipartFile, String version) {
        // 上传apk文件
        String fileName = "pigeon-" + version + ".apk";
        String filePath = APK_DIR_PATH + fileName;
        log.info("上传apk文件,路径为"+ filePath);
        try{
            multipartFile.transferTo(new File(filePath));
        }catch (Exception e){
            e.printStackTrace();
            return new UniversalResponseBody<>(ResponseResultEnum.FAILED.getCode(),ResponseResultEnum.FAILED.getMsg());
        }

        // 将apk信息写入数据库
        Apk apk = new Apk();
        apk.setVersion(version);
        apk.setVersionPath(filePath);
        int i = apkMapper.insertApk(apk);
        if (i > 0) {
            return new UniversalResponseBody<String>(ResponseResultEnum.SUCCESS.getCode(),ResponseResultEnum.SUCCESS.getMsg(), filePath);
        }
        return new UniversalResponseBody<String>(ResponseResultEnum.FAILED.getCode(),ResponseResultEnum.FAILED.getMsg());
    }

    @Override
    public UniversalResponseBody<Boolean> getVersionLatest(String version) {
        // 获取版本号
        String nowVersion = apkMapper.getVersion();
        Boolean isLatest = nowVersion.equals(version);
        return new UniversalResponseBody<Boolean>(ResponseResultEnum.SUCCESS.getCode(),ResponseResultEnum.SUCCESS.getMsg(), !isLatest);
    }
}
