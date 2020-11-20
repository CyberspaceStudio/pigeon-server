package com.qingyuan.pigeon.config;

import com.qingyuan.pigeon.enums.ResponseResultEnum;
import com.qingyuan.pigeon.exception.ResponseException;
import com.qingyuan.pigeon.utils.UniversalResponseBody;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 异常类捕获
 * @program: pigeon_server
 * @author: GuoShuSong
 * @create: 2020-11-12 19:17
 **/
@ControllerAdvice
public class ExceptionAdvice {


    @ExceptionHandler(ResponseException.class)
    @ResponseBody
    public UniversalResponseBody exceptionHadler(ResponseException e){
        return new UniversalResponseBody(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(IOException.class)
    @ResponseBody
    public UniversalResponseBody ioExceptionHandler(IOException e){
        return new UniversalResponseBody(ResponseResultEnum.FILE_IO_EXCEPTION.getCode(),ResponseResultEnum.FILE_IO_EXCEPTION.getMsg());
    }


}
