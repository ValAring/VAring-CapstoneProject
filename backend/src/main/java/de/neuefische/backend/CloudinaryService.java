package de.neuefische.backend;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public String uploadImage(MultipartFile image) throws IOException {
        File fileUpload = File.createTempFile("image", null);
        image.transferTo(fileUpload);

        Map response = cloudinary.uploader().upload(fileUpload, Collections.emptyMap());

        return response.get("url").toString();
    }
}