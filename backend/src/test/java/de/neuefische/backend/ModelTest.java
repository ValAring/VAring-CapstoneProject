package de.neuefische.backend;

import de.neuefische.backend.model.Project;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModelTest {
    private static Project createProject() {
        return new Project("i", "a", "t", "yesterday", "today");
    }

    @Test
    void testWithId                  () { assertEquals(new Project(" ", "a", "t", "yesterday", "today"), createProject().withId         (" ")); }
    @Test void testWithAuthor        () { assertEquals(new Project("i", " ", "t", "yesterday", "today"), createProject().withAuthor     (" ")); }
    @Test void testWithDescription   () { assertEquals(new Project("i", "a", " ", "yesterday", "today"), createProject().withDescription(" ")); }
    @Test void testWithtimeCreated   () { assertEquals(new Project("i", "a", "t", " ", "today"), createProject().withTimeCreated  (" ")); }
    @Test void testWithlastEdited    () { assertEquals(new Project("i", "a", "t", "yesterday", " "), createProject().withLastEdited  (" ")); }


}
