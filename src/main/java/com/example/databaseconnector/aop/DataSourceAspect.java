package com.example.databaseconnector.aop;

import com.example.databaseconnector.context.DatabaseContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataSourceAspect {
    @Pointcut("@annotation(com.example.databaseconnector.aop.ReadDatabase)")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.example.databaseconnector.aop.WriteDatabase)")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DatabaseContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DatabaseContextHolder.master();
    }
}
