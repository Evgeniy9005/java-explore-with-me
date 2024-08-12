package ru.practicum.users.request.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.data.Controller;

import static org.junit.jupiter.api.Assertions.*;

class ParticipationRequestDtoTest extends Controller {

    private ParticipationRequestDto participationRequestDto;

    @BeforeEach
    void setUp() {
        initUser(1);
        initCategory(1);
        initEventWithParam(1);
        initParticipationRequest(1);
        participationRequestDto = participationRequestDtoMap.get(1);
    }

    @Test
    void getId() {
        assertEquals(1,participationRequestDto.getId());
    }

    @Test
    void getCreated() {
        assertNotNull(participationRequestDto.getCreated());
    }

    @Test
    void getEvent() {
        assertNotNull(participationRequestDto.getEvent());
    }

    @Test
    void getRequester() {
        assertNotNull(participationRequestDto.getRequester());
    }

    @Test
    void getStatus() {
        assertNotNull(participationRequestDto.getStatus());
    }
}