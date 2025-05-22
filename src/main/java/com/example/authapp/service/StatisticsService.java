package com.example.authapp.service;

import com.example.authapp.model.Specification;
import com.example.authapp.repository.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Service
public class StatisticsService {

    @Autowired
    private SpecificationRepository specificationRepository;

    public Map<String, Object> getStatistics(Long userId) {
        Map<String, Object> statistics = new HashMap<>();
        
        try {
            // Get total documents count
            long totalDocuments = specificationRepository.count();
            
            // Get in-progress documents count (where progress < 100)
            long inProgressDocuments = specificationRepository.count();
            
            // Calculate days active (from first document creation)
            Specification firstDocument = specificationRepository.findFirstByOrderByCreatedAtAsc();
            long daysActive = 0;
            
            if (firstDocument != null && firstDocument.getCreatedAt() != null) {
                daysActive = ChronoUnit.DAYS.between(
                    firstDocument.getCreatedAt(),
                    LocalDateTime.now()
                );
            }
            
            statistics.put("totalDocuments", totalDocuments);
            statistics.put("inProgressDocuments", inProgressDocuments);
            statistics.put("daysActive", daysActive);
            statistics.put("status", "success");
            
        } catch (Exception e) {
            statistics.put("status", "error");
            statistics.put("message", "Error calculating statistics: " + e.getMessage());
            System.err.println(e);
        }
        
        return statistics;
    }
} 