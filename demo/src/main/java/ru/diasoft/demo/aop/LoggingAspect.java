package ru.diasoft.demo.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

@Component
@Aspect
public class LoggingAspect {
    // Logger
    private static Logger logger = LogManager.getLogger();

    private final static Set<Class<?>> errorClassSet = new HashSet<>();

    @Pointcut("within(@Loggable *))")
    public void loggableClasses() {
    }

    @Pointcut("@annotation(Loggable)")
    public void loggableMethods() {
    }

    @Before("loggableClasses() || loggableMethods()")
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className  = joinPoint.getSignature().getDeclaringTypeName();

        logger.info("Method {}.{} with parameters: {}", className, methodName, Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "loggableClasses() || loggableMethods()", returning = "result")
    public void afterDebug(final JoinPoint joinPoint, final Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className  = joinPoint.getSignature().getDeclaringTypeName();
        String value = "";

        if(result != null){
            if (result.getClass().isArray()) {
                Object[] objects = (Object[]) result;
                StringBuilder sb = new StringBuilder();
                for (Object obj : objects) {
                    if (obj == null) {
                        continue;
                    }
                    if (errorClassSet.contains(obj.getClass())) {
                        logger.warn("The pretty json is not support for class {}", result.getClass().getCanonicalName());
                        continue;
                    }
                    try {
                        sb.append(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj))
                                .append("\n");
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
                value = sb.toString();
            }

            try {
                value = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        logger.info("Method {}.{}() return : {}", className, methodName, value);
    }

}
