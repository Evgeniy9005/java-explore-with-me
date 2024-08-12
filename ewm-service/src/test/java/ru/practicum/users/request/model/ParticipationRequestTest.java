package ru.practicum.users.request.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.data.Controller;

import static org.junit.jupiter.api.Assertions.*;

class ParticipationRequestTest extends Controller {

    private ParticipationRequest participationRequest;

    @BeforeEach
    void setUp() {
        initUser(1);
        initCategory(1);
        initEventWithParam(1);
        initParticipationRequest(1);
        participationRequest = participationRequestList.get(0);
    }

    @Test
    void getId() {
        assertEquals(1,participationRequest.getId());
    }

    @Test
    void getCreated() {
        assertNotNull(participationRequest.getId());
    }

    @Test
    void getEvent() {
        assertNotNull(participationRequest.getEvent());
    }

    @Test
    void getRequester() {
        assertNotNull(participationRequest.getEvent());
    }

    @Test
    void getStatus() {
        assertNotNull(participationRequest.getEvent());
    }
}