package de.neuefische.backend;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import de.neuefische.backend.model.Project;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DashboardIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ProjectRepository projectRepository;

    @MockBean
    Cloudinary cloudinary;
    Uploader uploader = mock(Uploader.class);

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

        MockMultipartFile data = new MockMultipartFile("data",
                null,
                MediaType.APPLICATION_JSON_VALUE,
                """
                                    {
                                        "author": "Author1",
                                        "description": "MyDescription1",
                                        "imageURL": "testImage.png"
                                    }
                                """
                        .getBytes()
        );
        MockMultipartFile file = new MockMultipartFile("file",
                "testImage.png",
                MediaType.IMAGE_PNG_VALUE,
                "testImage".getBytes()
        );
        File fileToUpload = File.createTempFile("image", null);
        file.transferTo(fileToUpload);

        when(cloudinary.uploader()).thenReturn(uploader);
        when(uploader.upload(any(), any())).thenReturn(Map.of("url", "testImage.png"));

        mockMvc.perform(multipart("/api")
                        .file(data)
                        .file(file))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                                    {
                                        "author": "Author1",
                                        "description": "MyDescription1",
                                        "imageURL": "testImage.png"
                                    }
                                """))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    @DirtiesContext
    void whenEditProject_getsInvalidID_returnsBadRequest(){
        // Given
        projectRepository.save(new Project("id1",  "Author 1", "Desc 1", "URL 1", "Posted 1", "Edited 1"));
        projectRepository.save(new Project("id2",  "Author 2", "Desc 2", "URL 2", "Posted 2", "Edited 2"));
        projectRepository.save(new Project("id3",  "Author 3", "Desc 3", "URL 3", "Posted 3", "Edited 3"));
        projectRepository.save(new Project("id4",  "Author 4", "Desc 4", "URL 4", "Posted 4", "Edited 4"));

        // When
        assertThrows(ServletException.class, () ->
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .put("/api/project/id1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
							{ "id": "id2", "author": "Author 1B", "description": "Desc 1", "imageURL": "URL 1", "timeCreated": "Posted 1", "lastEdited": "Edited 1"}
						""")
                    )

                    // Then
                    .andExpect(status().isBadRequest())
        );
    }

    @Test
    @DirtiesContext
    void whenEditProject_getsUnknownID_returnsNotFound(){
        // Given
        projectRepository.save(new Project("id1",  "Author 1", "Desc 1", "URL 1", "Posted 1", "Edited 1"));
        projectRepository.save(new Project("id2",  "Author 2", "Desc 2", "URL 2", "Posted 2", "Edited 2"));
        projectRepository.save(new Project("id3",  "Author 3", "Desc 3", "URL 3", "Posted 3", "Edited 3"));
        projectRepository.save(new Project("id4",  "Author 4", "Desc 4", "URL 4", "Posted 4", "Edited 4"));

        // When
        assertThrows(ServletException.class, () ->
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/project/id10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
							{ "id": "id10", "author": "Author 1B", "description": "Desc 1", "imageURL": "URL 1", "timeCreated": "Posted 1", "lastEdited": "Edited 1"}
						""")
                )

                // Then
                .andExpect(status().isNotFound())
                );
    }

    @Test
    @DirtiesContext
    void whenEditeProject_getsValidID_returnsChangedBook() throws Exception {
        // Given
        projectRepository.save(new Project("id1",  "Author 1", "Desc 1", "URL 1", "Posted 1", "Edited 1"));
        projectRepository.save(new Project("id2",  "Author 2", "Desc 2", "URL 2", "Posted 2", "Edited 2"));
        projectRepository.save(new Project("id3",  "Author 3", "Desc 3", "URL 3", "Posted 3", "Edited 3"));
        projectRepository.save(new Project("id4",  "Author 4", "Desc 4", "URL 4", "Posted 4", "Edited 4"));

        // When
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/project/id1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
							{ "id": "id1", "author": "Author 1B", "description": "Desc 1", "imageURL": "URL 1", "timeCreated": "Posted 1", "lastEdited": "Edited 1"}
						""")
                )

                // Then
                .andExpect(status().isOk())
                .andExpect(content().json("""
					{ "id": "id1", "author": "Author 1B", "description": "Desc 1", "imageURL": "URL 1", "timeCreated": "Posted 1", "lastEdited": "Edited 1"}
						"""));
    }

    @Test
    @DirtiesContext
    void removeBookTest() throws Exception {
        projectRepository.save(new Project("123", "A", "T", "URL","then", "now"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/project/123"))
                .andExpect(status().isOk());

    }

    @Test
    void testHandleNoSuchElementException () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/handleNoSuchElementException"))
                .andExpect(status().isNotFound());
    }
}
