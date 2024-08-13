package ru.practicum.compilations.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.data.Data;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompilationTest {

    private Compilation compilation;

    @BeforeEach
    void setUp() {
        compilation = Data.<Compilation>generationData(1,Compilation.class, List.of(1,2)).get(0);
    }

    @Test
    void getId() {
        assertNotNull(compilation.getId());
    }

    @Test
    void getEvents() {
        assertEquals("[1, 2]",compilation.getEvents());
    }

    @Test
    void isPinned() {
        assertTrue(compilation.isPinned());
    }

    @Test
    void getTitle() {
        assertNotNull(compilation.getTitle());
    }

    @Test
    void setId() {
        compilation.setId(2);
        assertEquals(2,compilation.getId());
    }

    @Test
    void setEvents() {
        compilation.setEvents(String.valueOf(List.of(1)));
        assertEquals("[1]",compilation.getEvents());
    }

    @Test
    void setPinned() {
        compilation.setId(2);
        assertEquals(2,compilation.getId());
    }

    @Test
    void setTitle() {
        compilation.setId(2);
        assertEquals(2,compilation.getId());
    }
}