package com.haxe.backend.project;

import java.util.List;

import org.springframework.stereotype.Service;

import com.haxe.backend.exception.DuplicateResourceException;
import com.haxe.backend.exception.ResourceNotFoundException;
import com.haxe.backend.utilities.Sort;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getProjects(
            Sort sort,
            Integer limit) {
        return projectRepository.findAll();
    }

    public Project getProjectById(int id) {
        return projectRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Project with this id does not exist."));
    }

    public void deleteProjectById(Integer id) {
        projectRepository.deleteById(id);
    }

    public void addProject(Project project) {
        if (projectRepository.existsByName(project.getName())) {
            throw new DuplicateResourceException("Project name is already taken.");
        }
        Project newProject = new Project(
                project.getName(),
                project.getProduct(),
                project.getLanguage(),
                project.getProjectOwner(),
                project.getBusinessCriticality(),
                project.getRepoUrl(),
                project.getEnvironment(),
                null,
                project.getSecurityTools());

        projectRepository.save(newProject);
    }

    public void updateProject(Integer id, Project project) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project with this id does not exist."));

        if (!existingProject.getName().equals(project.getName()) && projectRepository.existsByName(project.getName())) {
            throw new DuplicateResourceException("Project name is already taken.");
        }

        existingProject.setName(project.getName());
        existingProject.setProduct(project.getProduct());
        existingProject.setLanguage(project.getLanguage());
        existingProject.setBusinessCriticality(project.getBusinessCriticality());
        existingProject.setProjectOwner(project.getProjectOwner());
        existingProject.setRepoUrl(project.getRepoUrl());
        existingProject.setEnvironment(project.getEnvironment());
        existingProject.setRequirements(project.getRequirements());
        existingProject.setSecurityTools(project.getSecurityTools());

        projectRepository.save(existingProject);
    }
}
