package com.tomasfonta.heroes.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogElapsedTimeAspect {

    @Around("@annotation(LogElapsedTimeConfig)")
    public Object logElapsedTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long time = System.currentTimeMillis() - startTime;
        log.info(String.format(":::::::::: Executing %s, -  Elapsed time: %s ::::::::::", joinPoint.getSignature(), time));
        return proceed;
    }

}
