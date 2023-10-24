package de.neuefische.backend;

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
    void whenAddProjects_getsNewProject_ReturnProject() throws Exception{
        //GIVEN
        //WHEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"author\":\"Author1\",\"description\":\"MyDescription1\"}")
                )
                //THEN
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"author\":\"Author1\",\"description\":\"MyDescription1\"}"))
                .andExpect(jsonPath("$.id").isString());
    }
}
