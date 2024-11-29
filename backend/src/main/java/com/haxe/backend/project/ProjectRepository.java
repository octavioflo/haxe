package com.haxe.backend.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    boolean existsByName(String name);
}

// ProjectRepository {
// private List<Project> projects = new ArrayList<>();

// private AtomicInteger idCounter = new AtomicInteger(0);

// {
// projects.add(new Project(idCounter.incrementAndGet(), "haxe", "octavioflo",
// Language.JAVA));
// projects.add(new Project(idCounter.incrementAndGet(), "coldpizza",
// "octavioflo", Language.JAVA));
// }

// public AtomicInteger getIdCounter() {
// return idCounter;
// }

// public List<Project> getProjects() {
// return projects;
// }
// }
