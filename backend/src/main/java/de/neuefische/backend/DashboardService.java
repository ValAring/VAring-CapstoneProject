package de.neuefische.backend;

import de.neuefische.backend.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final ProjectRepository projectRepository;
    private final DateTimeService dateTimeService = new DateTimeService();

    public List<Project> getAllProjects() {return projectRepository.findAll();}
    public Project addProject(Project newProject) {
        Project project = new Project(
                null,
                newProject.author(),
                newProject.description(),
                dateTimeService.getCurrentDateTime().toString(),
                dateTimeService.getCurrentDateTime().toString()
        );
        return projectRepository.save(project);
    }
}
