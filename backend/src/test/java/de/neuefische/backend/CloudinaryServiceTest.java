package de.neuefische.backend;
import com.cloudinary.Cloudinary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CloudinaryServiceTest {

    @Mock
    private Cloudinary cloudinary;
    private CloudinaryService cloudinaryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cloudinaryService = new CloudinaryService(cloudinary);
    }

    @Test
    void testUploadImage() throws IOException {
        // Create a mock MultipartFile for testing
        MultipartFile mockFile = createMockMultipartFile();

        // Create a map to simulate Cloudinary's response
        Map<String, Object> cloudinaryResponse = Collections.singletonMap("url", "https://example.com/image.jpg");

        // Mock Cloudinary's upload method
        when(cloudinary.uploader().upload(any(File.class), any(Map.class)))
                .thenReturn(cloudinaryResponse);

        String imageUrl = cloudinaryService.uploadImage(mockFile);

        assertEquals("https://example.com/image.jpg", imageUrl);
    }

    MultipartFile createMockMultipartFile() {
        // Create a mock MultipartFile for testing
        // You can use a testing library like MockMultipartFile from Spring for this purpose
        // Example: MockMultipartFile file = new MockMultipartFile("file", "test-image.jpg", "image/jpeg", "Some image data".getBytes());
        // Ensure the file's content type and name match the actual file
        return new MockMultipartFile("file", "test-image.jpg", "image/jpeg", "Some image data".getBytes());
    }
}
