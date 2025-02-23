package com.thomas.JobApp.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceMonitorAspect.class);

    //suppose if client is sending a request with postid negative , using aroung we are making it positive and return back
    @Around("execution(* com.thomas.JobApp.service.JobService.getJob(..)) && args(postId)")
    public Object validateAndUpdate(ProceedingJoinPoint jp, int postId) throws Throwable {

            if(postId <0){
                LOGGER.info("postId is negative , updating it ");
                postId = -postId;
                LOGGER.info("New postId is "+postId);
            }

            Object obj = jp.proceed(new Object[]{postId});
            return obj;

    }

}
