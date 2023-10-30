package de.neuefische.backend;

import de.neuefische.backend.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final ProjectRepository projectRepository;
    private final DateTimeService dateTimeService = new DateTimeService();

    public List<Project> getAllProjects() {return projectRepository.findAll();}
    public Project getProjectById(String id) {

        Optional<Project> optionalProject = projectRepository.findById(id);
        if(optionalProject.isPresent()){
            return optionalProject.get();
        } else {
            throw new NoSuchElementException("Project nicht gefunden");
        }
    }
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

    public void removeProject(String id) {
        projectRepository.deleteById(id);
    }
}
