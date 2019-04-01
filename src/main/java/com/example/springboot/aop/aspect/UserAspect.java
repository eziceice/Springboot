package com.example.springboot.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {
    @Pointcut("execution (* com.example.springboot.aop.service.UserServiceImpl.printUser(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint)
    {
        System.out.println("before.....");
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
