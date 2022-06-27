package com.example.cloudprojectcollection.config;

import com.example.cloudprojectcollection.exceptions.BizException;
import com.example.cloudprojectcollection.result.CommonResult;
import com.example.cloudprojectcollection.result.ExceptionCode;
import com.fasterxml.jackson.databind.JsonMappingException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Wwx
 * @createTime: 2022年06月27日
 * @version: 0.0.1
 * @Description: 全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    public static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("class.*", "*.class.*", "Class.*", "*.Class.*");
    }
    /**
     * 全局异常处理
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public CommonResult<BizException> bizExceptionHandler(HttpServletRequest req, BizException e) {
        LOGGER.error(e.getMessage(), e);
        return new CommonResult<>(e.getCode(), e.getMessage(), null);
    }

    /**
     * 处理其他运行时异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public CommonResult<RuntimeException> runtimeExceptionHandler(HttpServletRequest req, RuntimeException e) {
        LOGGER.error("系统异常: " + e.getMessage(), e);
        return CommonResult.failed(ExceptionCode.FAILED);
    }


    /**
     * json格式解析异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = JsonMappingException.class)
    @ResponseBody
    public CommonResult<RuntimeException> jsonMappingExceptionHandler(HttpServletRequest req, JsonMappingException e) {
        LOGGER.error("服务json参数解析异常: " + e.getMessage(), e);
        return CommonResult.failed(ExceptionCode.JSON_TRANS_FAILED);
    }

    /**
     * 服务调用异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = FeignException.class)
    @ResponseBody
    public CommonResult<RuntimeException> feignExceptionHandler(HttpServletRequest req, FeignException e) {
        LOGGER.error("本服务调用其他服务异常:{}", e);
        return CommonResult.failed(ExceptionCode.SERVICE_INVOCATION_FAILED);
    }

}
