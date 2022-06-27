package com.example.cloudprojectcollection.result;

/**
 * @Author: Wwx
 * @createTime: 2022年06月27日 12:30:27
 * @version: 0.0.1
 * @Description: 2022-06-27
 */

/**
 * {
 *
 *         "data": null,             结果集(出现异常,结果集为空)
 *
 *         "code": 500,            状态码
 *
 *         "message": ""          状态码的描述
 *
 * }
 */
public class CommonResult<T> {
    /**
     * 结果
     */
    private T data;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态码描述
     */
    private String message;

    public CommonResult() {}

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    protected CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     */
    public static <T> CommonResult<T> success() {
        return new CommonResult<T>(ExceptionCode.SUCCESS.getCode(), ExceptionCode.SUCCESS.getMessage());
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ExceptionCode.SUCCESS.getCode(), ExceptionCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ExceptionCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param exceptionCode 错误码
     */
    public static <T> CommonResult<T> failed(ExceptionCode exceptionCode) {
        return new CommonResult<T>(exceptionCode.getCode(), exceptionCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> CommonResult<T> failed(Integer errorCode, String message) {
        return new CommonResult<T>(errorCode, message, null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(ExceptionCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return failed(ExceptionCode.FAILED.getMessage());
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ExceptionCode.FAILED.getMessage());
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(ExceptionCode.FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(ExceptionCode.FAILED.getCode(), ExceptionCode.FAILED.getMessage(), data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
