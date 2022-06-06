package mate.academy.springboot.aop.aspect;

import java.util.Arrays;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import mate.academy.springboot.aop.model.Category;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(public * mate.academy.springboot.aop.service..*(..))")
    public void allServiceMethods() {
    }

    @Before("allServiceMethods()")
    public void beforeServiceMethodAdvice(JoinPoint joinPoint) {
        log.info(String.format(
                "Method %s.%s was called. Arguments: %s",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs())
        ));
    }

    @After("execution(* mate.academy.springboot.aop.service.CategoryService.findAll())")
    public void afterCategoryServiceFindAllMethodAdvice() {
        log.info("Method CategoryService.findAll finished its execution.");
    }

    @AfterReturning(
            pointcut = "execution(* mate.academy.springboot.aop.service.CategoryService.findAll())",
            returning = "result"
    )
    public void afterServiceMethodAdvice(List<Category> result) {
        log.info("Method CategoryService.findAll finished its execution with the result size " + result.size());
    }
}