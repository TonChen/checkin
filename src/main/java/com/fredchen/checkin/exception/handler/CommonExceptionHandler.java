package com.fredchen.checkin.exception.handler;

import com.fredchen.checkin.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: fredchen
 * @Date: 2018/1/15 16:43
 */

@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {


    @ExceptionHandler(CommonException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handle(CommonException e){
        log.info("common exception had occurred");
        return e.getErrorMessage();
    }



}
