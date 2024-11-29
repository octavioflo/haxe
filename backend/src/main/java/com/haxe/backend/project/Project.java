package com.haxe.backend.project;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
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
    private Language language;

    public Project() {
    }

    public Project(Integer pid, String name, String product, Language language) {
        this.id = pid;
        this.name = name;
        this.product = product;
        this.language = language;
    }

    public Project(String name, String product, Language language) {
        this.name = name;
        this.product = product;
        this.language = language;
    }

    /**
     * @return int return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the group
     */
    public String getProduct() {
        return product;
    }

    /**
     * @param group the group to set
     */
    public void setProduct(String group) {
        this.product = group;
    }

    /**
     * @return Language return the language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

}
