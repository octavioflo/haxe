package com.haxe.backend.project;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.haxe.backend.exception.DuplicateResourceException;
import com.haxe.backend.exception.ResourceNotFoundException;
import com.haxe.backend.utilities.Sort;

import jakarta.validation.Valid;

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

        // return projectRepository.getProjects().stream()
        // .filter(projects -> projects.getId() == id)
        // .findFirst()
        // .orElseThrow(() -> new ResourceNotFoundException("Project with this id does
        // not exist."));
    }

    public void deleteProjectById(Integer id) {
        projectRepository.deleteById(id);
        // projectRepository.getProjects().removeIf(projects -> projects.getId() == id);
    }

    public void addProject(Project project) {
        if (!projectRepository.existsByName(project.getName())) {
            throw new DuplicateResourceException("Project name is already taken.");
        }
        Project newProject = new Project(project.getName(), project.getProduct(), project.getLanguage());

        projectRepository.save(newProject);
        // projectRepository.getProjects()
        // .add(
        // new Project(
        // projectRepository.getIdCounter().incrementAndGet(),
        // project.getName(),
        // project.getGroup(),
        // project.getLanguage()));
    }

    public void updateProject(Integer id, Project project) {
        // int index = id - 1;
        // projectRepository.getProjects()
        // .set(index,
        // new Project(
        // id,
        // project.getName(),
        // project.getGroup(),
        // project.getLanguage()));
    }
}
