package com.bite.spingbookdemo.model;


import com.bite.spingbookdemo.enums.ResultCodeEnum;
import lombok.Data;

@Data
public class Result<T>  {
    private ResultCodeEnum code;
    private String errMsg;
    private T data;

    public static<T> Result success(T data) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.SUCCESS);
        result.setData(data);
        return result;
    }
    public static Result fail(String errMsg) {
        Result result = new Result();
        result.setErrMsg(errMsg);
        result.setCode(ResultCodeEnum.FAIL);
        return result;
    }
    public static Result unlogin() {
        Result result = new Result();
        result.setCode(ResultCodeEnum.UNLOGIN);
        result.setErrMsg("用户未登录");
        result.setData(null);
        return result;
    }
}
