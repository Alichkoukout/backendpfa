package com.example.authapp.controller;


import com.example.authapp.model.Specification;
import com.example.authapp.repository.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specifications")
@CrossOrigin(origins = "*")
public class SpecificationController {

    @Autowired
    private SpecificationRepository specificationRepository;

    @GetMapping
    public List<Specification> getAllSpecifications() {
        return specificationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specification> getSpecificationById(@PathVariable Long id) {
        return specificationRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Specification createSpecification(@RequestBody Specification specification) {
        return specificationRepository.save(specification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Specification> updateSpecification(
            @PathVariable Long id,
            @RequestBody Specification specificationDetails) {

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
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSpecification(@PathVariable Long id) {
        return specificationRepository.findById(id)
                .map(specification -> {
                    specificationRepository.delete(specification);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
