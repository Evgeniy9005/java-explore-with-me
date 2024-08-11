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
    }

    @Test
    void getEvents() {
    }

    @Test
    void isPinned() {
    }

    @Test
    void getTitle() {
    }

    @Test
    void setId() {
    }

    @Test
    void setEvents() {
    }

    @Test
    void setPinned() {
    }

    @Test
    void setTitle() {
    }
}