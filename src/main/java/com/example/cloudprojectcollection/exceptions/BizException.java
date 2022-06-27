package com.example.cloudprojectcollection.exceptions;

/**
 * @Author: Wwx
 * @createTime: 2022年06月27日
 * @version: 0.0.1
 * @Description: 自定义一场处理类
 */

public class BizException extends RuntimeException{
    private String code;

    public BizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(String code, String message, Object... args) {
        super(String.format(message, args));
        this.code = code;
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
