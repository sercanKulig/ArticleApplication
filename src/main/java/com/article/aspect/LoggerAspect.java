package com.article.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


@Aspect
@Component
@EnableAspectJAutoProxy
public class LoggerAspect {
    private static Logger logger = Logger.getLogger(LoggerAspect.class);

    @Pointcut("execution(* com.article.*.*.*(..))")
    private void generalPointcut() {

    }

    @AfterThrowing(pointcut = "generalPointcut() throw Exception", throwing = "ex")
    public void exceptionLog(JoinPoint joinPoint, Exception ex) throws Exception {
        logger.info(joinPoint.getTarget().getClass().getSimpleName() + " : " + joinPoint.getSignature().getName()
        + " : " + ex.getMessage());
    }

    @Before("generalPointcut()")
    public void beforeInfoLog(JoinPoint joinPoint) {
        logger.info(joinPoint.getTarget().getClass().getSimpleName() + " : " + joinPoint.getSignature().getName());
    }

/*    @Around("generalPointcut()")
    public void aroundInfoLog(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(Arrays.toString(joinPoint.getArgs()));
        joinPoint.proceed(); //continue on the intercepted method
    }*/
}
