package com.bit.blog.common.exception;

import lombok.Data;

@Data
public class BlogException extends RuntimeException {
    private int code;
    private String errMsg;

    public BlogException(String message, Throwable cause, String errMsg, int code) {
        super(message, cause);
        this.errMsg = errMsg;
        this.code = code;
    }
    public BlogException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }
}
