package com.haxe.backend.project;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository {
    private List<Project> projects = new ArrayList<>();

    private AtomicInteger idCounter = new AtomicInteger(0);

    {
        projects.add(new Project(idCounter.incrementAndGet(), "haxe", "octavioflo", Language.JAVA));
        projects.add(new Project(idCounter.incrementAndGet(), "coldpizza", "octavioflo", Language.JAVA));
    }

    public AtomicInteger getIdCounter() {
        return idCounter;
    }

    public List<Project> getProjects() {
        return projects;
    }
}
