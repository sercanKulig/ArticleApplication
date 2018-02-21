package com.article.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
@EnableAspectJAutoProxy
public class LoggerAspect {
    private static Logger logger = Logger.getLogger(LoggerAspect.class);

    @Pointcut("execution(* com.article.*.*.*(..))")
    private void generalPointcut() {

    }

    @AfterThrowing(pointcut = "generalPointcut() throw Exception", throwing = "ex")
    public void exceptionLog(JoinPoint joinPoint, Throwable ex) {
        logger.info(joinPoint.getTarget().getClass().getSimpleName() + " : " + joinPoint.getSignature().getName()
                + " : " + ex.getMessage());
    }

    @Before("generalPointcut()")
    public void beforeInfoLog(JoinPoint joinPoint) {
        logger.info(joinPoint.getTarget().getClass().getSimpleName() + " : " + joinPoint.getSignature().getName());
    }

    @Around("generalPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter: {}.{}() with argument[s] = {}" + joinPoint.getSignature().getDeclaringTypeName() + " : " +
                    joinPoint.getSignature().getName() + " : " + Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (logger.isDebugEnabled()) {
                logger.debug("Exit: {}.{}() with result = {}" + " : " + joinPoint.getSignature().getDeclaringTypeName() + " : " +
                        joinPoint.getSignature().getName() + " : " + result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            logger.error("Illegal argument: {} in {}.{}()" + " : " + Arrays.toString(joinPoint.getArgs()) + " : " +
                    joinPoint.getSignature().getDeclaringTypeName() + " : " + joinPoint.getSignature().getName());

            throw e;
        }
    }
}
