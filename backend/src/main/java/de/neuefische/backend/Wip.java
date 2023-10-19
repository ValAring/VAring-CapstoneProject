package de.neuefische.backend;

import java.util.List;

public record Wip(
        String Id,
        String author,
        String stageDescription,
        List<String> ImageURLs,
        String timeCreated,
        String lastEdited
) {
}
