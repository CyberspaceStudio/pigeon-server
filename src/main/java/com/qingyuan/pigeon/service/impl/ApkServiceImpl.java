package com.qingyuan.pigeon.service.impl;

import com.qingyuan.pigeon.service.ApkService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author: qyl
 * @Date: 2020/11/17 10:45
 */
@Service
public class ApkServiceImpl implements ApkService {

    private static final String filename = "pigeon-#{version}.apk";
    private static final String path = "/a-pigeon/apk/";

    @Override
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {

        File file = new File(path, filename);

        if (file.exists()) {
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition","attachment;filename="+filename);

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
                System.out.println("SUCCESS");
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
}
