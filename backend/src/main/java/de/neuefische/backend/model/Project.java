package de.neuefische.backend.model;

import lombok.With;
import org.springframework.data.annotation.Id;

@With
public record Project(
        @Id
        String id,
        String author,
        String description,
        String imageURL,
        String timeCreated,
        String lastEdited
) {
}


