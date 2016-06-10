package com.mitrais.scrummit.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.mitrais.scrummit.bo.impl.BaseBOImpl;

@Aspect
public class BOAspect {
    @SuppressWarnings("rawtypes")
    @Before("execution(* com.mitrais.scrummit.bo..*BO.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        BaseBOImpl currentBO = (BaseBOImpl) joinPoint.getTarget();

        System.out.println("logBefore() is running!");
        System.out.println("bo class used : " + currentBO.getClass().getName());
        System.out.println("bo called method : " + joinPoint.getSignature().getName());
        System.out.println("bo use database : " + (currentBO.isCentralBO() ? "central" : "tenant"));
        if (currentBO.isCentralBO()) {
            currentBO.resolveCentral();
        } else {
            currentBO.resolveTenant();
        }
        System.out.println("******");
    }
}
