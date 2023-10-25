package de.neuefische.backend;

import de.neuefische.backend.model.Project;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModelTest {
    private static Project createProject() {
        return new Project("i", "a", "t", "yesterday", "today");
    }

    @Test void testWithId            () { assertEquals(new Project(" ", "a", "t", "yesterday", "today"), createProject().withId          (" ")); }
    @Test void testWhenAuthorChange_IDEqual() {
        Project originalProject = new Project("i", "a", "t", "yesterday", "today");
        Project newProject = originalProject.withAuthor("new");
        assertEquals(originalProject.id(), newProject.id());
    }

    @Test void testWithAuthor        () { assertEquals(new Project("i", " ", "t", "yesterday", "today"), createProject().withAuthor     (" ")); }
    @Test void testWhenDescChange_AuthorEqual() {
        Project originalProject = new Project("i", "a", "t", "yesterday", "today");
        Project newProject = originalProject.withDescription("new");
        assertEquals(originalProject.author(), newProject.author());
    }

    @Test void testWithDescription   () { assertEquals(new Project("i", "a", " ", "yesterday", "today"), createProject().withDescription(" ")); }
    @Test void testWhenAuthorChange_DescrEqual() {
        Project originalProject = new Project("i", "a", "t", "yesterday", "today");
        Project newProject = originalProject.withAuthor("new");
        assertEquals(originalProject.description(), newProject.description());
    }

    @Test void testWithTimeCreated   () { assertEquals(new Project("i", "a", "t", " ", "today"), createProject().withTimeCreated  (" ")); }
    @Test void testWhenAuthorChange_TimeCreatedEqual() {
        Project originalProject = new Project("i", "a", "t", "yesterday", "today");
        Project newProject = originalProject.withAuthor("new");
        assertEquals(originalProject.timeCreated(), newProject.timeCreated());
    }

    @Test void testWithLastEdited    () { assertEquals(new Project("i", "a", "t", "yesterday", " "), createProject().withLastEdited  (" ")); }
    @Test void testWhenAuthorChange_lastEditedEqual() {
        Project originalProject = new Project("i", "a", "t", "yesterday", "today");
        Project newProject = originalProject.withAuthor("new");
        assertEquals(originalProject.lastEdited(), newProject.lastEdited());
    }
}
