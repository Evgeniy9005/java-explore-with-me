package ru.practicum.users.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.data.Controller;

import static org.junit.jupiter.api.Assertions.*;

class EventRequestStatusUpdateResultTest extends Controller {

    @BeforeEach
    void setUp() {
        initUser(2);
        initCategory(1);
        initEventWithParam(1);
        initParticipationRequest(3);
    }

    @Test
    void addConfirmedRequests() {
        eventRequestStatusUpdateResult.addConfirmedRequests(participationRequestDtoMap.get(1));
    }

    @Test
    void addRejectedRequests() {
        eventRequestStatusUpdateResult.addRejectedRequests(participationRequestDtoMap.get(2));
    }

    @Test
    void getConfirmedRequests() {
        assertNotNull(eventRequestStatusUpdateResult.getConfirmedRequests());
    }

    @Test
    void getRejectedRequests() {
        assertNotNull(eventRequestStatusUpdateResult.getRejectedRequests());
    }
}