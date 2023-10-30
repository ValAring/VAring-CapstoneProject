package de.neuefische.backend;

import de.neuefische.backend.model.Project;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Project getProjectByID(@PathVariable String id){
        return dashboardService.getProjectById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project addProject(@RequestBody Project newProject){
        return dashboardService.addProject(newProject);
    }
    @DeleteMapping("/project/{id}")
    public void deleteProject(@PathVariable String id) {
        dashboardService.removeProject(id);
    }
}
