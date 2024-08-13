package ru.practicum.users;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import ru.practicum.data.Controller;
import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(controllers = UsersController.class)
class UsersControllerTest extends Controller {

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
        initUser(5);
        initCategory(2);
        initEventWithParam(3);
        initParticipationRequest(3);
    }



    @Test
    void getEventsAddedCurrentUser() throws Exception {
        when(userService.getEventsAddedCurrentUser(anyInt(),anyInt(),anyInt(),any())).thenReturn(eventShortDtoList);
        mvc.perform(get("/users/1/events")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("from","0")
                        .param("size","10"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService).getEventsAddedCurrentUser(anyInt(),anyInt(),anyInt(),any());
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
        when(userService.getFullInfoAboutEventAddedByCurrentUser(anyInt(),anyInt(),any()))
                .thenReturn(eventFullDtoMap.get(1));
        mvc.perform(get("/users/1/events/1")
                        .content(objectMapper.writeValueAsString(newEventDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService).getFullInfoAboutEventAddedByCurrentUser(anyInt(),anyInt(),any());
    }

    @Test
    void upEventAddedByCurrentUser() throws Exception {
        when(userService.upEventAddedByCurrentUser(any(),anyInt(),anyInt(),any())).thenReturn(eventFullDtoMap.get(1));
        mvc.perform(patch("/users/1/events/1")
                        .content(objectMapper.writeValueAsString(updateEventUserRequest))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService).upEventAddedByCurrentUser(any(),anyInt(),anyInt(),any());
    }

    @Test
    void getInformationRequestsToParticipateCurrentUserEvent() throws Exception {
        when(userService.getInformationRequestsToParticipateCurrentUserEvent(anyInt(),anyInt(),any()))
                .thenReturn(participationRequestDtoList);
        mvc.perform(get("/users/1/events/1/requests")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService).getInformationRequestsToParticipateCurrentUserEvent(anyInt(),anyInt(),any());
    }

    @Test
    void upStatusApplicationsParticipationEventCurrentUser() throws Exception {
        when(userService.upStatusApplicationsParticipationEventCurrentUser(any(),anyInt(),anyInt(),any()))
                .thenReturn(eventRequestStatusUpdateResult);
        mvc.perform(patch("/users/1/events/1/requests")
                        .content(objectMapper.writeValueAsString(eventRequestStatusUpdateRequest))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService).upStatusApplicationsParticipationEventCurrentUser(any(),anyInt(),anyInt(),any());
    }

    @Test
    void getInfoCurrentUserRequestsParticipateOtherPeopleEvents() throws Exception {
        when(userService.getInfoCurrentUserRequestsParticipateOtherPeopleEvents(anyInt(),any()))
                .thenReturn(participationRequestDtoList);
        mvc.perform(get("/users/1/requests")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService).getInfoCurrentUserRequestsParticipateOtherPeopleEvents(anyInt(),any());
    }

    @Test
    void addRequestCurrentUserParticipateEvent() throws Exception {
        when(userService.addRequestCurrentUserParticipateEvent(anyInt(),anyInt(),any()))
                .thenReturn(participationRequestDtoMap.get(1));
        mvc.perform(post("/users/1/requests")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("eventId","1"))
                .andDo(print())
                .andExpect(status().isCreated());
        verify(userService).addRequestCurrentUserParticipateEvent(anyInt(),anyInt(),any());
    }

    @Test
    void upEventToParticipateCancel() throws Exception {
        when(userService.upEventToParticipateCancel(anyInt(),anyInt(),any()))
                .thenReturn(participationRequestDtoMap.get(1).toBuilder().status("CANCELED").build());
        mvc.perform(patch("/users/1/requests/1/cancel")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService).upEventToParticipateCancel(anyInt(),anyInt(),any());
    }
}