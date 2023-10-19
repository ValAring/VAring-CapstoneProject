package de.neuefische.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public Project addProject(Project newProject){
        return projectRepository.save(newProject.withId(null));
    }
}
