package ru.practicum.admin.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.data.Data;

import static org.junit.jupiter.api.Assertions.*;

class UpdateEventAdminRequestTest {

    private UpdateEventAdminRequest updateEventAdminRequest;

    @BeforeEach
    void start() {
        updateEventAdminRequest = Data.<UpdateEventAdminRequest>generationData(1,UpdateEventAdminRequest.class).get(0);
    }

    @Test
    void notNullGetAnnotation() {
        assertNotNull(updateEventAdminRequest);
        assertEquals("Краткое описание 1",updateEventAdminRequest.getAnnotation());
    }

    @Test
    void getCategory() {
        assertNotNull(updateEventAdminRequest.getAnnotation());
    }

    @Test
    void getDescription() {
        assertNotNull(updateEventAdminRequest.getDescription());
    }

    @Test
    void getEventDate() {
        assertNotNull(updateEventAdminRequest.getEventDate());
    }

    @Test
    void getLocation() {
        assertNotNull(updateEventAdminRequest.getLocation());
    }

    @Test
    void getPaid() {
        assertNotNull(updateEventAdminRequest.getPaid());
    }

    @Test
    void getParticipantLimit() {
        assertNotNull(updateEventAdminRequest.getParticipantLimit());
    }

    @Test
    void getRequestModeration() {
        assertNotNull(updateEventAdminRequest.getRequestModeration());
    }

    @Test
    void getStateAction() {
        assertNotNull(updateEventAdminRequest.getStateAction());
    }

    @Test
    void getTitle() {
        assertNotNull(updateEventAdminRequest.getTitle());
    }
}