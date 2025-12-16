package com.diwakarallu.ecommerce.notification.utility;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.diwakarallu.ecommerce.notification.service.*Impl.*(..))")
    public void logBefore() {
        log.info("Before method execution");
    }

    @After("execution(* com.diwakarallu.ecommerce.notification.service.*Impl.*(..))")
    public void logAfter() {
        log.info("After method execution");
    }

    @AfterThrowing(pointcut = "execution(* com.diwakarallu.ecommerce.notification.service.*Impl.*(..))", throwing = "exception")
    public void logAfterThrowing(Exception exception) {
        log.info("After method execution" + exception.getMessage());
    }

    @AfterReturning("execution(* com.diwakarallu.ecommerce.notification.service.*Impl.*(..))")
    public void logAfterReturning() {
        log.info("After method execution");
    }
}
