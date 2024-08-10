package ru.practicum.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.admin.AdminController;
import ru.practicum.client.StatsClient;
import ru.practicum.data.Controller;
import ru.practicum.users.dto.UserDto;
import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UsersController.class)
class UsersControllerTest extends Controller {

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
        initUser(5);
        initCategory(2);
        initEventWithParam(3);
    }



    @Test
    void getEventsAddedCurrentUser() {
    }

    @Test
    void addEventUser() throws Exception {
        when(userService.addEventUser(any(),anyInt(),any())).thenReturn(eventFullDtoMap.get(1));
        mvc.perform(post("/users/1/events")
                        .content(objectMapper.writeValueAsString(newEventDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
        verify(userService).addEventUser(any(),anyInt(),any());
    }

    @Test
    void getFullInfoAboutEventAddedByCurrentUser() throws Exception {
    }

    @Test
    void upEventAddedByCurrentUser() throws Exception {
    }

    @Test
    void getInformationRequestsToParticipateCurrentUserEvent() throws Exception {
    }

    @Test
    void upStatusApplicationsParticipationEventCurrentUser() throws Exception {

    }

    @Test
    void getInfoCurrentUserRequestsParticipateOtherPeopleEvents() throws Exception {
    }

    @Test
    void addRequestCurrentUserParticipateEvent() throws Exception {
    }

    @Test
    void upEventToParticipateCancel() throws Exception {
    }
}