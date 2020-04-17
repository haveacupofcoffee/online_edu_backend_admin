package com.codingforfun.servicebase.handler.exceptionhandler;

import com.codingforfun.commonutils.ResponseResult;
import com.codingforfun.servicebase.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * handle exceptions across the whole application
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * handle exception
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult error(Exception e) {
        log.error(e.getMessage());
        return ResponseResult.error().message("Global Exception!!");
    }

    /**
     * handle custom exceptions
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult error(CustomException e) {
        log.error(e.getExceptionMessage());
        return ResponseResult.error().code(e.getCode()).message(e.getExceptionMessage());
    }







}
