package de.neuefische.backend;

import lombok.With;

import java.util.List;

@With
public record Wip(
        String Id,
        String author,
        String stageDescription,
        List<String> ImageURLs,
        String timeCreated,
        String lastEdited
) {
}
