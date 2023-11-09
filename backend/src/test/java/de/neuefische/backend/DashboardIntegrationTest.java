package de.neuefische.backend;

import de.neuefische.backend.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DashboardIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ProjectRepository projectRepository;

    @Test
    @DirtiesContext
    void whenGetAllProjects_performsOnEmptyRepo_returnsEmptyJsonArray() throws Exception {
        // Given
        // When
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api")
                )

                // Then
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
    @Test
    @DirtiesContext
    void getProjectByID_ifFound() throws Exception {
        //GIVEN
        String id= "1";
        Project book = new Project(id,"Author 1","Desc 1", "URL","time 1", "time 2");
        projectRepository.save(book);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/project/"+ id))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
					{
						"id"         : "1",
						"author"     : "Author 1",
						"description": "Desc 1",
						"imageURL"   : "URL",
						"timeCreated": "time 1",
					    "lastEdited" : "time 2"
					}
				"""));
    }

    @Test
    @DirtiesContext
    void getProjectByID_ifNotFound_handleNoSuchElementException() throws Exception {
        //GIVEN
        String id= "3";

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/"+ id))

                //THEN
                .andExpect(status().isNotFound());
    }

    @Test
    @DirtiesContext
    void whenAddProjects_getsNewProject_ReturnProject() throws Exception{
        //GIVEN
        //WHEN
        mockMvc
                .perform(MockMvcRequestBuilders.post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"author\":\"Author1\",\"description\":\"MyDescription1\",\"imageURL\":\"URL\"}")
                )
                //THEN
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"author\":\"Author1\",\"description\":\"MyDescription1\",\"imageURL\":\"URL\"}"))
                .andExpect(jsonPath("$.id").isString());
    }

    @Test
    @DirtiesContext
    void removeBookTest() throws Exception {
        projectRepository.save(new Project("123", "A", "T", "URL","then", "now"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/project/123"))
                .andExpect(status().isOk());

    }
}
