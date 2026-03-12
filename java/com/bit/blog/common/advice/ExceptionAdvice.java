package com.bit.blog.common.advice;

import com.bit.blog.common.exception.BlogException;
import com.bit.blog.pojo.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;

import java.util.logging.Handler;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler
    public Result exceptionHandler(Exception exception) {
        log.error("发生异常,e",exception);
        return Result.fail(exception.getMessage());
    }
    @ExceptionHandler
    public Result exceptionHandler(BlogException exception) {
        log.error("发生异常,e",exception);
        return Result.fail(exception.getErrMsg());
    }
    @ExceptionHandler
    public Result exceptionHandler(HandlerMethodValidationException exception) {
        log.error("发生异常,e:{}",exception.getMessage());
        return Result.fail("参数校验失败");
    }
    @ExceptionHandler
    public Result exceptionHandler(MethodArgumentNotValidException exception) {
        String msg=exception.getBindingResult().getFieldError().getDefaultMessage();
        log.error("发生异常,e:{}",msg);
        return Result.fail(msg);
    }


}
