package com.tugra.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.tugra.service.HelloService.sayHello(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("📌 Method çalışıyor: {}", joinPoint.getSignature().getName());
        logger.info("🔢 Parametreler: {}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* com.tugra.service.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("✅ Method tamamlandı: {}", joinPoint.getSignature().getName());
        logger.info("📤 Dönüş: {}", result);
    }

    @AfterThrowing(pointcut = "execution(* com.tugra.service.*.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("❌ Hata oluştu: {}", joinPoint.getSignature().getName());
        logger.error("🪲 Hata mesajı: {}", error.getMessage(), error);
    }

    @Around("execution(* com.tugra.service.*.*(..))")
    public Object logAround(org.aspectj.lang.ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("⏳ Başlıyor: {}", joinPoint.getSignature().getName());
        Object result = joinPoint.proceed(); // Metot burda çalışıyor. --- JoinPoint'un bir alt türüdür, metodun gerçekten çağrılması için proceed() metodunu çağırmak gerekir.
        logger.info("⏹️ Bitti: {}", joinPoint.getSignature().getName());
        return result;
    }

    // Aldığım Notlar //

    // @Around ile başladı ve başladı yazdırdı.
    // @Before ile sayHello yazdırdı.
    // @Before parametreleri yazdırdı.
    // @AfterReturning ile sayHello tamamlandı yazdırdı.
    // @AfterReturning ile dönüş değerini yazdırdı.
    // @Around ile bitti yazdırdı ve bitti.

}
