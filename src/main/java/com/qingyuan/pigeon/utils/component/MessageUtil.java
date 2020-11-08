package com.qingyuan.pigeon.utils.component;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.qingyuan.pigeon.pojo.PO.AliyunResponsePO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: pigeon_server
 * @author: GuoShuSong
 * @create: 2020-11-08 15:26
 **/
@Component
@Slf4j
public class MessageUtil {

    private static final String accessKeyId= "LTAI4FtHHhFxRXNGn8EGCY4r";

    private static final String accessKeySecret = "VZEiKwqQnYtvK9up6FdUOolgeRdLbx";

    private static final String verifyCodeTemplateCode = "SMS_205431840";

    private static final String Domain = "dysmsapi.aliyuncs.com";

    private static final String Version = "2017-05-25";

    private static final String Action = "SendSms";

    private static final String SuccessCode = "OK";

    private static final String SignName = "鸽子窝";


    public boolean sendVerifyCode(String userTel,String code) throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(Domain);
        request.setVersion(Version);
        request.setAction(Action);
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", userTel);
        request.putQueryParameter("SignName", SignName);
        request.putQueryParameter("TemplateCode", verifyCodeTemplateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\""
                +"}");
        CommonResponse commonResponse = client.getCommonResponse(request);
        AliyunResponsePO aliyunResponseInfo = JSON.parseObject(commonResponse.getData(), AliyunResponsePO.class);
        if (aliyunResponseInfo.getCode().equals(SuccessCode)) {
            return true;
        } else {
            log.error(aliyunResponseInfo.toString() + "  验证码短息发送失败 电话:" + userTel + "code:" + code);
            return false;
        }
    }

}


