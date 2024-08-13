package ru.practicum.users.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.data.Controller;

import static org.junit.jupiter.api.Assertions.*;

class NewUserRequestTest extends Controller {

    private NewUserRequest newUserRequest;

    @BeforeEach
    void setUp() {
        initUser(2);
        initCategory(1);
        initEventWithParam(1);
        initParticipationRequest(3);
        newUserRequest = newUserRequestList.get(0);
    }

    @Test
    void getEmail() {
        assertNotNull(newUserRequest.getEmail());
    }

    @Test
    void getName() {
        assertNotNull(newUserRequest.getName());
    }
}