package de.neuefische.backend;

import lombok.With;

import java.util.List;

@With
public record Project(
        String id,
        String author,
        String description,
        List<Wip> wips
) {
}
