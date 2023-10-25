package de.neuefische.backend;

import de.neuefische.backend.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DashboardServiceTest {
    private ProjectRepository projectRepository;
    private DashboardService dashboardService;

    @BeforeEach
    void setUp(){
        projectRepository = mock(ProjectRepository.class);
        dashboardService = new DashboardService(projectRepository);
    }
    @Test
    void whenGetAllProjects_calledWithEmptyRepo_returnEmptyList() {
        // Given
        when(projectRepository.findAll()).thenReturn(List.of());

        // When
        List<Project> actual = dashboardService.getAllProjects();

        // Then
        verify(projectRepository).findAll();
        List<Project> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    void whenGetAllProjects_calledWithNonEmptyRepo_returnListOfRepoContent() {
        // Given
        when(projectRepository.findAll()).thenReturn(List.of(
                new Project("123","Author1","Title1","timestamp1","today1"),
                new Project("124","Author2","Title2","timestamp2","today2"),
                new Project("125","Author3","Title3","timestamp3","today3"),
                new Project("126","Author4","Title4","timestamp4","today4"),
                new Project("127","Author5","Title5","timestamp5","today5"),
                new Project("128","Author6","Title6","timestamp6","today6")
        ));

        // When
        List<Project> actual = dashboardService.getAllProjects();

        // Then
        verify(projectRepository).findAll();
        List<Project> expected = List.of(
                new Project("123","Author1","Title1", "timestamp1", "today1"),
                new Project("124","Author2","Title2", "timestamp2", "today2"),
                new Project("125","Author3","Title3", "timestamp3", "today3"),
                new Project("126","Author4","Title4", "timestamp4", "today4"),
                new Project("127","Author5","Title5", "timestamp5", "today5"),
                new Project("128","Author6","Title6", "timestamp6", "today6")
        );
        assertEquals(expected, actual);
    }
}
