package com.example.cloudprojectcollection.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author: Wwx
 * @createTime: 2022年06月27日
 * @version: 0.0.1
 * @Description: 拦截器
 */
@Component
@Aspect
public class AspectConfig {

    @Pointcut("execution(* com.example.cloudprojectcollection.service.*.*(..))")
    private void pointCut() {
    }

    //注解前置通知
    @Before("pointCut()")
    public Object before(JoinPoint joinPoint) {
        System.out.println("执行" + joinPoint.getSignature().toString() + "\r\n");
        return null;
    }

    //注解后置通知
    @AfterReturning(value = "pointCut()", returning = "result")
    public Object after(JoinPoint joinPoint, Object result) {
        assert result != null && result.toString() != null;
        System.out.println("执行结果：" + result.toString() + "\r\n");
        return result;
    }

    //注解抛出异常通知
    @AfterThrowing(value = "pointCut()", throwing = "throwable")
    public Object exceptionHandler(JoinPoint joinPoint, Throwable throwable) {
        System.out.println("结果异常：" + throwable.getMessage() + "\r\n");
        return null;
    }

    //注解最终通知
    @After("pointCut()")
    public Object afterAll(JoinPoint joinPoint) {
        return null;
    }

    //注解环绕通知，这个通知的增强内容相当于上面所有通知的并集
//    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            before(joinPoint);
            Object result = joinPoint.proceed();
            assert result != null;
            return after(joinPoint, result);
        } catch (Throwable throwable) {
            return exceptionHandler(joinPoint, throwable);
        } finally {
            afterAll(joinPoint);
        }
    }
}
