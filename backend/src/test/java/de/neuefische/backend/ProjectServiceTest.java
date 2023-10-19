package de.neuefische.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectServiceTest {

    private ProjectRepository projectRepository;
    private ProjectService projectService;

    @BeforeEach
    void setUp(){
        projectRepository = mock(ProjectRepository.class);
        projectService = new ProjectService(projectRepository);
    }


    @Test
    void addProject(){
        //GIVEN
        when(projectRepository.save(new Project(null, "Author1", "Paint a Painting")))
                .thenReturn(new Project("1", "Author1", "Paint a Painting"));

        //WHEN
        Project actual = projectService.addProject(new Project("12345678", "Author1", "Paint a Painting"));

        //THEN
        verify(projectRepository).save(new Project(null, "Author1", "Paint a Painting"));

        Project expected = new Project("1", "Author1", "Paint a Painting");
        assertEquals(expected, actual);
    }
}
