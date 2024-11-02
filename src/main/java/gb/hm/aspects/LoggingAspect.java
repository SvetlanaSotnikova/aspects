package gb.hm.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Around("execution(* gb.hm.services.*.*(..))")
    @Order(1)
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        logger.info("\nMethod: " + methodName +
                " args: " + Arrays.asList(args) +
                " will execute");
        Object result = joinPoint.proceed();
        logger.info("Method has done");
        return result;
    }

    @Around("execution(* gb.hm.services.*.*(..))")
    @Order(2)
    public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis() - startTime;
        logger.info("Method " + joinPoint.getSignature().getName() + " - " + endTime + " ms");
        return result;
    }

    @AfterReturning(value = "@annotation(ToLog)", returning = "returned")
    public void log(JoinPoint joinPoint, Object returned) {
        logger.info("Method done and returned " + returned);
    }
}
