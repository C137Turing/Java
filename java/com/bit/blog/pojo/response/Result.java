package com.bit.blog.pojo.response;

import com.bit.blog.enums.ResultCodeEnum;
import lombok.Data;

@Data
public class Result {
    private ResultCodeEnum code;
    private String errMsg;
    private Object data;

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.SUCCESS);
        result.setData(data);
        return result;
    }
    public static Result fail(String msg) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.FAIL);
        result.setErrMsg(msg);
        return result;
    }
    public static Result fail(String msg, Object data) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.FAIL);
        result.setErrMsg(msg);
        result.setData(data);
        return result;
    }
}
