package ru.practicum.compilations.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.data.Controller;

import static org.junit.jupiter.api.Assertions.*;

class CompilationDtoTest extends Controller {
    private CompilationDto compilationDto;

    @BeforeEach
    void setUp() {
        initUser(1);
        initCategory(1);
        initEventWithParam(1);
        initCompilation(1);
        compilationDto = compilationDtoMap.get(1);
    }

    @Test
    void getId() {
        assertEquals(1,compilationDto.getId());
    }

    @Test
    void getEvents() {
        assertEquals(eventShortDtoMap.get(1),compilationDto.getEvents().get(0));
    }

    @Test
    void isPinned() {
        assertTrue(compilationDto.isPinned());
    }

    @Test
    void getTitle() {
        assertNotNull(compilationDto.getTitle());
    }
}