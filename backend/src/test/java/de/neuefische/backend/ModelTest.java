package de.neuefische.backend;

import de.neuefische.backend.model.Project;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ModelTest {
    private static Project createProject() {
        return new Project("i", "a", "t", "yesterday", "today");
    }

    @Test void testWithId            () { assertEquals(new Project(" ", "a", "t", "yesterday", "today"), createProject().withId          (" ")); }
    @Test void testWhenIdChange_IDNotEqual() {
        Project originalProject = new Project("i", "a", "t", "yesterday", "today");
        Project newProject = originalProject.withId("new");
        assertNotEquals(originalProject.id(), newProject.id());
    }

    @Test void testWithAuthor        () { assertEquals(new Project("i", " ", "t", "yesterday", "today"), createProject().withAuthor     (" ")); }
    @Test void testWhenAuthorChange_AuthorNotEqual() {
        Project originalProject = new Project("i", "a", "t", "yesterday", "today");
        Project newProject = originalProject.withAuthor("new");
        assertNotEquals(originalProject.author(), newProject.author());
    }

    @Test void testWithDescription   () { assertEquals(new Project("i", "a", " ", "yesterday", "today"), createProject().withDescription(" ")); }
    @Test void testWhenDescrChange_DescrNotEqual() {
        Project originalProject = new Project("i", "a", "t", "yesterday", "today");
        Project newProject = originalProject.withDescription("new");
        assertNotEquals(originalProject.description(), newProject.description());
    }

    @Test void testWithTimeCreated   () { assertEquals(new Project("i", "a", "t", " ", "today"), createProject().withTimeCreated  (" ")); }
    @Test void testWhenTimeCreatedChange_TimeCreatedNotEqual() {
        Project originalProject = new Project("i", "a", "t", "yesterday", "today");
        Project newProject = originalProject.withTimeCreated("new");
        assertNotEquals(originalProject.timeCreated(), newProject.timeCreated());
    }

    @Test void testWithLastEdited    () { assertEquals(new Project("i", "a", "t", "yesterday", " "), createProject().withLastEdited  (" ")); }
    @Test void testWhenLastEditedChange_lastEditedEqual() {
        Project originalProject = new Project("i", "a", "t", "yesterday", "today");
        Project newProject = originalProject.withLastEdited("new");
        assertNotEquals(originalProject.lastEdited(), newProject.lastEdited());
    }
}
