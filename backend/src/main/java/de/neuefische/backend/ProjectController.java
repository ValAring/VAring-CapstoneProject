package de.neuefische.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public Project addProject(@RequestBody Project newProject){return projectService.addProject(newProject);}




}
