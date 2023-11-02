package de.neuefische.backend;

import com.cloudinary.Cloudinary;
import de.neuefische.backend.model.Project;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CloudinaryIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CloudinaryService cloudinaryService;
    @MockBean
    private Cloudinary cloudinary;

    /*@Test
    @DirtiesContext
    void testUploadImage() throws Exception {

        MockMultipartFile json = new MockMultipartFile("data", null, MediaType.APPLICATION_JSON_VALUE, """
                {
                    "id": "",
                    "url": "url"
                }""".getBytes());
        MockMultipartFile file = new MockMultipartFile("file", "".getBytes());

        mockMvc.perform(multipart("/api")
                .file(json)
                .file(file))
                .andExpect(status().isOk());
    }*/

    /*@Test
    void testUploadImage() throws IOException {
        // Create a mock MultipartFile (you can use a testing library for this)
        MultipartFile mockFile = new MockMultipartFile("image", "image.jpg", "image/jpeg", "Some image data".getBytes());

        // Define the expected Cloudinary response
        Map<String, Object> cloudinaryResponse = Collections.singletonMap("url", "https://example.com/image.jpg");

        // Mock the Cloudinary service to return the expected response
        Mockito.when(cloudinary.uploader().upload(Mockito.any(File.class), Mockito.anyMap()))
                .thenReturn(cloudinaryResponse);

        // Perform the uploadImage operation
        String imageUrl = cloudinaryService.uploadImage(mockFile);

        // Verify that the service correctly handled the upload and returned the expected URL
        assertEquals("https://example.com/image.jpg", imageUrl);
    }*/
}
