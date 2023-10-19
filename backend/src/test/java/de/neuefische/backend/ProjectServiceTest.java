package de.neuefische.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    void whenGetAllProjects_calledWithEmptyRepo_returnEmptyList() {
        // Given
        when(projectRepository.findAll()).thenReturn(List.of());

        // When
        List<Project> actual = projectService.getAllProjects();

        // Then
        verify(projectRepository).findAll();
        List<Project> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    void whenGetAllProjects_calledWithNonEmptyRepo_returnListOfRepoContent() {
        // Given
        when(projectRepository.findAll()).thenReturn(List.of(
                new Project("123","Author1","Title1"),
                new Project("124","Author2","Title2"),
                new Project("125","Author3","Title3"),
                new Project("126","Author4","Title4"),
                new Project("127","Author5","Title5"),
                new Project("128","Author6","Title6")
        ));

        // When
        List<Project> actual = projectService.getAllProjects();

        // Then
        verify(projectRepository).findAll();
        List<Project> expected = List.of(
                new Project("123","Author1","Title1"),
                new Project("124","Author2","Title2"),
                new Project("125","Author3","Title3"),
                new Project("126","Author4","Title4"),
                new Project("127","Author5","Title5"),
                new Project("128","Author6","Title6")
        );
        assertEquals(expected, actual);
    }
}
