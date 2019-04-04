package com.example.springboot.aop.aspect;

import com.example.springboot.aop.pojo.UserJDBCTemplate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {
    @Pointcut("execution (* com.example.springboot.aop.service.UserServiceImpl.printUser(..))")
    public void pointCut() {
    }

    @Before("pointCut() && args(userJDBCTemplate)")
    public void before(JoinPoint joinPoint, UserJDBCTemplate userJDBCTemplate)
    {
        System.out.println("before.....");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("before around");
        jp.proceed();
        System.out.println("after around");
    }

    @After("pointCut()")
    public void after()
    {
        System.out.println("after.....");
    }

    @AfterReturning("pointCut()")
    public void afterReturning()
    {
        System.out.println("afterReturning.....");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing()
    {
        System.out.println("afterThrowing.....");
    }
}
