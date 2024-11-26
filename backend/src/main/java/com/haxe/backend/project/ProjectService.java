package com.haxe.backend.project;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
        if (sort == Sort.ASC) {
            return projectRepository.getProjects().stream()
                    .sorted(Comparator.comparing(Project::id))
                    .limit(limit)
                    .collect(Collectors.toList());
        }
        return projectRepository.getProjects().stream()
                .sorted(Comparator.comparing(Project::id).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public Optional<Project> getProjectById(int id) {
        return projectRepository.getProjects().stream()
                .filter(projects -> projects.id() == id)
                .findFirst();
    }

    public void deleteProjectById(Integer id) {
        projectRepository.getProjects().removeIf(projects -> projects.id() == id);
    }

    public void addProject(Project project) {
        projectRepository.getProjects()
                .add(
                        new Project(
                                projectRepository.getIdCounter().incrementAndGet(),
                                project.name(),
                                project.group(),
                                project.language()));
    }

    public void updateProject(Integer id, Project project) {
        if (project.name().isEmpty()
                || project.name() == null
                || project.group() == null
                || project.group().isEmpty()) {
            // return ResponseEntity.badRequest().build();
            return;
        }

        int index = id - 1;
        projectRepository.getProjects()
                .set(index,
                        new Project(
                                id,
                                project.name(),
                                project.group(),
                                project.language()));
    }
}
