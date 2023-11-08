package de.neuefische.backend.model;

import org.springframework.data.annotation.Id;
import java.util.List;

public record Wip(
        @Id
        String id,
        String wipText,
        List<String> imageURL,
        String posted,
        String edited

){
}