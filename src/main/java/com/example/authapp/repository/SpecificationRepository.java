package com.example.authapp.repository;


import com.example.authapp.model.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long> {
}
