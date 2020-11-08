package com.qingyuan.pigeon.pojo.PO;

import lombok.Data;

/**
 * 阿里云短信服务返回结果对象
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/5 18:51
 */
@Data
public class AliyunResponsePO {

    /**
     * 结果信息
     */
    private String Message;

    /**
     * 请求Id
     */
    private String RequestId;

    /**
     * 结果代码
     */
    private String Code;
}
