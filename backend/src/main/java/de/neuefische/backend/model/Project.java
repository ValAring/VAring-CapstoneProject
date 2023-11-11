package de.neuefische.backend.model;

import org.springframework.data.annotation.Id;

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


