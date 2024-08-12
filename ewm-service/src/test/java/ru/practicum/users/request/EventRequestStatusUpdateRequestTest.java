package ru.practicum.users.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.data.Controller;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventRequestStatusUpdateRequestTest extends Controller {

    @BeforeEach
    void setUp() {
        initUser(1);
        initCategory(1);
        initEventWithParam(1);
        initParticipationRequest(1);
    }

    @Test
    void getRequestIds() {
        assertEquals(List.of(1),eventRequestStatusUpdateRequest.getRequestIds());
    }

    @Test
    void getStatus() {
        assertNotNull(eventRequestStatusUpdateRequest.getRequestIds());
    }
}