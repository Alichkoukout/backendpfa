package com.example.authapp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Specification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;
    private String projectType;
    private String companyName;
    private String companyDescription;
    private String primaryObjective;
    private String budget;
    private String timeline;
    private String technicalRequirements;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "specification_id")
    private List<Section> sections;
}