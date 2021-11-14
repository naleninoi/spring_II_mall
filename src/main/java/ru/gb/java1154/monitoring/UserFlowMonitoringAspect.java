package ru.gb.java1154.monitoring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserFlowMonitoringAspect {

    @Pointcut("within(ru.gb.java1154.service.implementation.UserServiceImplementation)")
    private void monitorUserService() {
    }

    @Pointcut("execution(* ru.gb.java1154.service.implementation.UserServiceImplementation.loadUserByUsername(String))")
    private void monitorUsernameNotFoundEx() {
    }

    @After("monitorUserService()")
    public void showUserServiceLog(JoinPoint joinPoint) {
        System.out.println(joinPoint);
    }

    @AfterThrowing(value = "monitorUsernameNotFoundEx()", throwing = "exception")
    public void showUserNotFoundEx(JoinPoint joinPoint, Exception exception) {
        System.out.println(joinPoint);
        System.out.println(exception.getMessage());
    }
}
