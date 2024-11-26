package com.haxe.backend.project;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.haxe.backend.utilities.Sort;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getProjects(
            @RequestParam(value = "sort", required = false, defaultValue = "ASC") Sort sort,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
        return projectService.getProjects(sort, limit);
    }

    @GetMapping("{id}")
    public Optional<Project> getProjectById(@PathVariable("id") int id) {
        return projectService.getProjectById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProjectById(@PathVariable("id") Integer id) {
        projectService.deleteProjectById(id);
    }

    @PostMapping
    public void addProject(@RequestBody Project project) {
        projectService.addProject(project);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateProject(@PathVariable("id") Integer id, @RequestBody Project project) {
        projectService.updateProject(id, project);
        return ResponseEntity.ok().build();
    }
}
