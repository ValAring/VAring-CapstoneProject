package de.neuefische.backend;

import de.neuefische.backend.model.Project;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping
    public List<Project> allProjects(){
        return dashboardService.getAllProjects();
    }
    @GetMapping("/project/{id}")
    public Project getProjectByID(@PathVariable String id) throws NoSuchElementException {
        return dashboardService.getProjectById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project addProject(@RequestPart ("data") Project newProject, @RequestPart(name = "file", required = false) MultipartFile image) throws IOException {
        return dashboardService.addProject(newProject, image);
    }

    @DeleteMapping("/project/{id}")
    public void deleteProject(@PathVariable String id) {
        dashboardService.removeProject(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException() {
        return "ID doesn't exist";
    }
}
