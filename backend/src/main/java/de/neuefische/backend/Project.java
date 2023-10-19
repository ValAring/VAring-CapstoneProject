package de.neuefische.backend;

import lombok.With;

@With
public record Project(
        String id,
        String author,
        String description
) {
}
