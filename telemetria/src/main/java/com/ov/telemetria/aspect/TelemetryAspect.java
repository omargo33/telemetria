package com.ov.telemetria.aspect;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class TelemetryAspect {

    private final ObservationRegistry observationRegistry;

    @Around("@within(com.ov.telemetria.annotation.Traceable) || @annotation(com.ov.telemetria.annotation.Traceable)")
    public Object traceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = method.getName();
        String spanName = String.format("%s.%s", className, methodName);

        return Observation.createNotStarted(spanName, observationRegistry)
                .contextualName(spanName)
                .lowCardinalityKeyValue("component", "functional-telemetry")
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