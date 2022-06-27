package com.example.cloudprojectcollection.result;

/**
 * @author: wwx
 * @date: 2022-06-27$
 * @desc:
 **/
public class ExceptionCode {
    public static final ExceptionCode SUCCESS = new ExceptionCode("200", "操作成功");
    public static final ExceptionCode FAILED = new ExceptionCode("500", "系统异常");
    public static final ExceptionCode JSON_TRANS_FAILED = new ExceptionCode("10000", "json格式解析异常");
    public static final ExceptionCode SERVICE_INVOCATION_FAILED = new ExceptionCode("10001", "服务调用异常");

    private String code;
    private String message;

    public ExceptionCode(String code, String message) {
        this.code = code;
        this.message= message;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
