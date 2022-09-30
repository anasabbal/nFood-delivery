package com.example.fooddelivery.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ErrorAspect {
    /**
     * @AfterThrowing it is used to output a log when error occurs
     */
    @AfterThrowing(value = "execution(* *..*..*(..)) &&" + "(bean(*RestController) || bean(*Service) || bean(*Repository))" , throwing = "an" )
    public void throwingNull(DataAccessException an){
        // Exception handling (log output)
        log.error("DataAccessException has  {}", an.getMessage());
    }

}
