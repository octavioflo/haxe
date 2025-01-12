package com.haxe.backend.project;

import java.util.Set;

import com.haxe.backend.project.attributes.BusinessCriticality;
import com.haxe.backend.project.attributes.Environment;
import com.haxe.backend.project.attributes.Language;
import com.haxe.backend.project.attributes.SecurityTool;
import com.haxe.backend.requirements.Requirements;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty(message = "Name must not be empty.")
    private String name;

    @NotNull
    @NotEmpty
    private String product;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Language.class)
    @CollectionTable(name = "LANGUAGE", joinColumns = @JoinColumn(name = "PROJECT_ID"))
    @Column(name = "LANGUAGE")
    private Set<Language> language;

    private String projectOwner;
    private BusinessCriticality businessCriticality;
    private String repoUrl;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Environment.class)
    @CollectionTable(name = "ENVIRONMENT", joinColumns = @JoinColumn(name = "PROJECT_ID"))
    @Column(name = "ENVIRONMENT")
    private Set<Environment> environment;

    @ManyToOne
    @JoinColumn(name = "REQUIREMENTS_ID")
    private Requirements requirements;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = SecurityTool.class)
    @CollectionTable(name = "SECURITY_TOOLS", joinColumns = @JoinColumn(name = "PROJECT_ID"))
    @Column(name = "SECURITY_TOOL")
    private Set<SecurityTool> securityTools;

    public Project(String name, String product, Set<Language> language, String projectOwner,
            BusinessCriticality businessCriticality, String repoUrl, Set<Environment> environment, Requirements requirements,
            Set<SecurityTool> securityTools) {
        this.name = name;
        this.product = product;
        this.language = language;
        this.projectOwner = projectOwner;
        this.businessCriticality = businessCriticality;
        this.repoUrl = repoUrl;
        this.environment = environment;
        this.requirements = requirements;
        this.securityTools = securityTools;
    }
}
