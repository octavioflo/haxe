package com.haxe.backend.project;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.haxe.backend.exception.DuplicateResourceException;
import com.haxe.backend.exception.ResourceNotFoundException;
import com.haxe.backend.utilities.Sort;

public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProjects() {
        List<Project> projects = Arrays.asList(new Project(), new Project());
        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> result = projectService.getProjects(Sort.ASC, 10);

        assertEquals(2, result.size());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    public void testGetProjectById() {
        Project project = new Project();
        when(projectRepository.findById(1)).thenReturn(Optional.of(project));

        Project result = projectService.getProjectById(1);

        assertNotNull(result);
        verify(projectRepository, times(1)).findById(1);
    }

    @Test
    public void testGetProjectById_NotFound() {
        when(projectRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> projectService.getProjectById(1));
        verify(projectRepository, times(1)).findById(1);
    }

    @Test
    public void testDeleteProjectById() {
        projectService.deleteProjectById(1);

        verify(projectRepository, times(1)).deleteById(1);
    }

    @Test
    public void testAddProject() {
        Project project = new Project();
        project.setName("Test Project");
        when(projectRepository.existsByName("Test Project")).thenReturn(false);

        projectService.addProject(project);

        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    public void testAddProject_DuplicateName() {
        Project project = new Project();
        project.setName("Test Project");
        when(projectRepository.existsByName("Test Project")).thenReturn(true);

        assertThrows(DuplicateResourceException.class, () -> projectService.addProject(project));
        verify(projectRepository, times(0)).save(any(Project.class));
    }

    @Test
    public void testUpdateProject() {
        Project existingProject = new Project();
        existingProject.setName("Existing Project");
        when(projectRepository.findById(1)).thenReturn(Optional.of(existingProject));
        when(projectRepository.existsByName("New Project")).thenReturn(false);

        Project updatedProject = new Project();
        updatedProject.setName("New Project");

        projectService.updateProject(1, updatedProject);

        verify(projectRepository, times(1)).save(existingProject);
    }

    @Test
    public void testUpdateProject_NotFound() {
        when(projectRepository.findById(1)).thenReturn(Optional.empty());

        Project updatedProject = new Project();
        updatedProject.setName("New Project");

        assertThrows(ResourceNotFoundException.class, () -> projectService.updateProject(1, updatedProject));
        verify(projectRepository, times(0)).save(any(Project.class));
    }

    @Test
    public void testUpdateProject_DuplicateName() {
        Project existingProject = new Project();
        existingProject.setName("Existing Project");
        when(projectRepository.findById(1)).thenReturn(Optional.of(existingProject));
        when(projectRepository.existsByName("New Project")).thenReturn(true);

        Project updatedProject = new Project();
        updatedProject.setName("New Project");

        assertThrows(DuplicateResourceException.class, () -> projectService.updateProject(1, updatedProject));
        verify(projectRepository, times(0)).save(any(Project.class));
    }
}