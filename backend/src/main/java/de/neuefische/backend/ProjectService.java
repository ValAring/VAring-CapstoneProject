package de.neuefische.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public Project addProject(Project newProject){
        return projectRepository.save(newProject.withId(null));
    }

    public List<Project> getAllProjects() {return projectRepository.findAll();}
}
