package com.example.authapp.controller;

import com.example.authapp.model.Specification;
import com.example.authapp.repository.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specifications")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class SpecificationController {

    @Autowired
    private SpecificationRepository specificationRepository;

    @GetMapping
    public ResponseEntity<List<Specification>> getAllSpecifications() {
        try {
            List<Specification> specifications = specificationRepository.findAll();
            return ResponseEntity.ok(specifications);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specification> getSpecificationById(@PathVariable Long id) {
        try {
            return specificationRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Specification> createSpecification(@RequestBody Specification specification) {
        try {
            Specification savedSpecification = specificationRepository.save(specification);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSpecification);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Specification> updateSpecification(
            @PathVariable Long id,
            @RequestBody Specification specificationDetails) {
        try {
            return specificationRepository.findById(id)
                    .map(specification -> {
                        specification.setProjectName(specificationDetails.getProjectName());
                        specification.setProjectType(specificationDetails.getProjectType());
                        specification.setCompanyName(specificationDetails.getCompanyName());
                        specification.setCompanyDescription(specificationDetails.getCompanyDescription());
                        specification.setPrimaryObjective(specificationDetails.getPrimaryObjective());
                        specification.setBudget(specificationDetails.getBudget());
                        specification.setTimeline(specificationDetails.getTimeline());
                        specification.setTechnicalRequirements(specificationDetails.getTechnicalRequirements());
                        specification.setSections(specificationDetails.getSections());

                        Specification updatedSpecification = specificationRepository.save(specification);
                        return ResponseEntity.ok(updatedSpecification);
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSpecification(@PathVariable Long id) {
        try {
            return specificationRepository.findById(id)
                    .map(specification -> {
                        specificationRepository.delete(specification);
                        return ResponseEntity.ok().build();
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
