package com.ov.telemetria.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Aspecto de telemetría para rastrear la ejecución de métodos anotados con @Traceable.
 * Utiliza Micrometer Observation para crear spans y registrar metadatos.
 * 
 * @author omargo33
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class TelemetryAspect {

    private final ObservationRegistry observationRegistry;

    /**
     * Intercepta la ejecución de métodos anotados con @Traceable o dentro de clases anotadas con @Traceable.
     * 
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@within(com.ov.telemetria.annotation.Traceable) || @annotation(com.ov.telemetria.annotation.Traceable)")
    public Object traceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = method.getName();
        String spanName = String.format("%s.%s", className, methodName);

        return Observation.createNotStarted(spanName, observationRegistry)
                .contextualName(spanName)
                .lowCardinalityKeyValue("component", "telemetria funcional")
                .lowCardinalityKeyValue("class", className)
                .lowCardinalityKeyValue("method", methodName)
                .observe(() -> {
                    try {
                        Object result = joinPoint.proceed();
                        log.debug("Trace executed: {}", spanName);
                        return result;
                    } catch (Throwable t) {
                        throw new RuntimeException(t);
                    }
                });
    }
}