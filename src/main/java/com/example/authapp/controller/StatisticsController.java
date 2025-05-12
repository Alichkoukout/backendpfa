package com.example.authapp.controller;

import com.example.authapp.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.OPTIONS})
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getStatistics() {
        try {
            Map<String, Object> statistics = statisticsService.getStatistics(null);
            
            if ("error".equals(statistics.get("status"))) {
                return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(statistics);
            }
            
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            Map<String, Object> errorResponse = Map.of(
                "status", "error",
                "message", "Failed to fetch statistics: " + e.getMessage()
            );
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
        }
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options() {
        return ResponseEntity.ok().build();
    }
} 