package com.haxe.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public enum Language {
		JAVA, PYTHON
	}

	public record Project(int id, String name, String group, Language language) {

	}

	public static List<Project> projects = new ArrayList<>();
	static {
		projects.add(new Project(1, "haxe", "octavioflo", Language.JAVA));
		projects.add(new Project(2, "coldpizza", "octavioflo", Language.JAVA));
	}

	@GetMapping("/projects")
	public List<Project> getProjects() {
		return projects;
	}

}
