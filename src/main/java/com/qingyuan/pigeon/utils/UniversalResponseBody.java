package com.qingyuan.pigeon.utils;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/1 13:36
 */

public class UniversalResponseBody<T> {

    /**
     * 结果代码
     */
    private Integer code;
    /**
     * 结果信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    public UniversalResponseBody(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public UniversalResponseBody(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "code:" + code +
                "msg:" + msg +
                "data:" + data +
                '}';
    }
}
