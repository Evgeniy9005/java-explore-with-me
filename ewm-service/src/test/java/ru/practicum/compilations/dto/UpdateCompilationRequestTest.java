package ru.practicum.compilations.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

//import static org.junit.jupiter.api.Assertions.*;

class UpdateCompilationRequestTest {

    private UpdateCompilationRequest updateCompilationRequest;

    @BeforeEach
    void start() {
        updateCompilationRequest = UpdateCompilationRequest.builder()
                .pinned(true)
                .events(List.of(1,2))
                .title("заголовок")
                .build();
    }

    @Test
    void getEvents() {
        updateCompilationRequest.getEvents();
    }

    @Test
    void getPinned() {
        updateCompilationRequest.getPinned();
    }

    @Test
    void getTitle() {
        updateCompilationRequest.getTitle();
    }
}