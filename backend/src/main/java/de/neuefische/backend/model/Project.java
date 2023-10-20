package de.neuefische.backend.model;

import lombok.With;

@With
public record Project(
        String id,
        String author,
        String description,
        String timeCreated,
        String lastEdited
) {
}
