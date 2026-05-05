package com.ov.telemetria.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Clase de anotación personalizada para marcar métodos o clases que deben ser rastreados por el aspecto de telemetría.
 * Permite agregar metadatos adicionales a las observaciones, como el nombre del span.
 * 
 * @author omargo33
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Traceable {
    String value() default "";
}