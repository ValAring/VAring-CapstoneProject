package de.neuefische.backend.model;

public record Project(
        String id,
        String author,
        String description,
        String timeCreated,
        String lastEdited
) {
}
