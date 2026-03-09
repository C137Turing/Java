package com.bite.spingbookdemo.config;


import com.bite.spingbookdemo.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler
    public Result handler(Exception e) {
        log.info("exception", e);
        return Result.fail("内部问题,请联系工作人员处理");
    }
}
