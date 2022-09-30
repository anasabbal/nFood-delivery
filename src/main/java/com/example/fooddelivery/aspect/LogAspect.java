package com.example.fooddelivery.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LogAspect {
    /**
     * @Before Executes the process before the target method is executed.
     * @After Executes the process after the target method is executed.
    /**
     * Log output before executing the service
     * Target: [CustomerService] is included in the class name
     */
    @Before("execution(* *..*.*CustomerService.*(..))")
    public void startLog(JoinPoint jp){
        log.info("Method start: "+  jp.getSignature());
    }
    /**
     * Log output after execution the service
     * Target [CustomerService] is included in the class name
     */
    @After("execution(* *..*.*CustomerService.*(..))")
    public void endLog(JoinPoint jp){
        log.info("Method end: " + jp.getSignature());
    }
    /** Log output before and after the controller is executed **/
    //@Around("bean(*Controller)")
    //@Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")

    /**
     * @Around you can insert processing before and after the AOP
     */
    @Around("@within(org.springframework.stereotype.Controller)" )
    public Object startLog(ProceedingJoinPoint jp) throws Throwable {
        // Output start log
        log .info("Method start: " + jp .getSignature());
        try {
            // Method execution
            Object result = jp.proceed();
            // Output end log
            log .info("Method end: " + jp .getSignature());
            // Return the execution result to the caller
            return result ;
        } catch (Exception e ) {
            // Output error log
            log .error("Method abend: " + jp .getSignature());
            // Rethrow the error
            throw e ;
        }
    }

}
