package de.neuefische.backend;

import de.neuefische.backend.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final ProjectRepository projectRepository;
    private final CloudinaryService cloudinaryService;
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
    public Project addProject(Project newProject, MultipartFile image) throws IOException {

        String url = null;
        if (image != null) {
            url = cloudinaryService.uploadImage(image);
        }

        Project project = new Project(
                null,
                newProject.author(),
                newProject.description(),
                url,
                dateTimeService.getCurrentDateTime().toString(),
                dateTimeService.getCurrentDateTime().toString()
        );
        return projectRepository.save(project);
    }

    public Project editProject(String id, Project project){
        if (!id.equals(project.id()))
            throw new IllegalArgumentException("editProject( id:%s, project:{ id:%s } ) -> given Id and Id of project are different".formatted(id, project.id()));

        Optional<Project> existingBook = projectRepository.findById(id);
        if (existingBook.isEmpty())
            throw new NoSuchElementException("editProject( id:%s, project:{ id:%s } ) -> Can't find a project Id \"%s\"".formatted(id, project.id(), project.id()));

        return projectRepository.save(project);
    }

    public void removeProject(String id) {
        projectRepository.deleteById(id);
    }
}
