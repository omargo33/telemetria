package com.ov.telemetria.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ov.telemetria.annotation.Traceable;

import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para verificar el estado de salud del servicio
 * 
 * @author omargo33
 */
@Slf4j
@RestController
@Traceable
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = Map.of(
                "service.name", "telemetria",
                "service.version", "1.0.0",
                "status", "healthy",
                "timestamp", LocalDateTime.now().toString()
        );
        log.info("Health check accessed");
        return ResponseEntity.ok(response);
    }
}