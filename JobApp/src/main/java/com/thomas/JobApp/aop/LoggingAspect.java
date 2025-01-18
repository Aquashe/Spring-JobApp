package com.thomas.JobApp.aop;


import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory .getLogger(LoggingAspect.class);

    @Before("execution(* com.thomas.JobApp.service.JobService.getJob(..)) || execution(* com.thomas.JobApp.service.JobService.getAllJobs(..))")
    public  void  logMethodCall(JoinPoint jp){
        LOGGER.info("Method called "+jp.getSignature().getName());
    }

    @After("execution(* com.thomas.JobApp.service.JobService.getJob(..)) || execution(* com.thomas.JobApp.service.JobService.getAllJobs(..))")
    public  void  logMethodCallAfter(JoinPoint jp){
        LOGGER.info("Method called after  "+jp.getSignature().getName());
    }

    @AfterThrowing("execution(* com.thomas.JobApp.service.JobService.getJob(..)) || execution(* com.thomas.JobApp.service.JobService.getAllJobs(..))")
    public  void  logMethodCallAfterThrowing(JoinPoint jp){
        LOGGER.info("Method "+jp.getSignature().getName()+" is having some error");
    }

    @AfterReturning("execution(* com.thomas.JobApp.service.JobService.getJob(..)) || execution(* com.thomas.JobApp.service.JobService.getAllJobs(..))")
    public  void  logMethodCallAfterReturning(JoinPoint jp){
        LOGGER.info("Method "+jp.getSignature().getName()+"executed succesfully");
    }
}
