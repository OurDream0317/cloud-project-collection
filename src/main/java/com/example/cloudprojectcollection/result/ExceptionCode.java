package com.example.cloudprojectcollection.result;

/**
 * @author: wwx
 * @date: 2022-06-27$
 * @desc:
 **/
public class ExceptionCode {
    public static final ExceptionCode SUCCESS = new ExceptionCode(200, "操作成功");
    public static final ExceptionCode FAILED = new ExceptionCode(500, "系统异常");

    private int code;
    private String message;

    public ExceptionCode(int code, String message) {
        this.code = code;
        this.message= message;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
