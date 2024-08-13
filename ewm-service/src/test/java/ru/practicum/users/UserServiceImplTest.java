package ru.practicum.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.category.dao.CategoryRepository;
import ru.practicum.compilations.dao.CompilationRepository;
import ru.practicum.constants.State;
import ru.practicum.constants.StatusRequest;
import ru.practicum.data.Controller;
import ru.practicum.events.dao.EventsRepository;
import ru.practicum.events.dto.EventFullDto;
import ru.practicum.events.model.Event;
import ru.practicum.users.dao.UserRepository;
import ru.practicum.users.request.EventRequestStatusUpdateResult;
import ru.practicum.users.request.dao.RequestRepository;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest extends Controller {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private EventsRepository eventsRepository;

    @Mock
    private RequestRepository requestRepository;

    @Mock
    private CompilationRepository compilationRepository;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository,
                eventsRepository,
                eventsMapper,
                categoryRepository,
                requestRepository,
                requestMapper);

        initUser(5);
        initCategory(1);
        initEventWithParam(3);
        initParticipationRequest(3);
    }

    @Test
    void getEventsAddedCurrentUser() {
        when(eventsRepository.findByInitiatorId(anyInt(),any())).thenReturn(eventList);
        userService.getEventsAddedCurrentUser(1,0,10,request);
        verify(eventsRepository).findByInitiatorId(anyInt(),any());

    }

    @Test
    void addEventUser() {
        when(eventsRepository.save(any())).thenReturn(eventList.get(0));
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(categoryList.get(0)));
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(userList.get(0)));
        userService.addEventUser(newEventDto,1,request);
        verify(eventsRepository).save(any());
        verify(categoryRepository).findById(anyInt());
        verify(userRepository).findById(anyInt());
    }

    @Test
    void getFullInfoAboutEventAddedByCurrentUser() {
        when(eventsRepository.findByInitiatorIdAndId(anyInt(),anyInt())).thenReturn(Optional.of(eventList.get(0)));
        EventFullDto eventFullDto = userService.getFullInfoAboutEventAddedByCurrentUser(1,1,request);
        assertEquals(eventFullDto,eventFullDtoMap.get(1));
        verify(eventsRepository).findByInitiatorIdAndId(anyInt(),anyInt());
    }

    @Test
    void upEventAddedByCurrentUser() {
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(categoryList.get(0)));
        when(eventsRepository.findById(anyInt())).thenReturn(Optional.of(eventList.get(0)));
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(userList.get(0)));
        userService.upEventAddedByCurrentUser(updateEventUserRequest,1,1,request);
        verify(eventsRepository).findById(anyInt());
        verify(categoryRepository).findById(anyInt());
        verify(userRepository).findById(anyInt());
    }

    @Test
    void getInformationRequestsToParticipateCurrentUserEvent() {
        when(requestRepository.findByEventInitiatorIdAndEventId(anyInt(),anyInt())).thenReturn(participationRequestList);
        userService.getInformationRequestsToParticipateCurrentUserEvent(1,1,request);
        verify(requestRepository).findByEventInitiatorIdAndEventId(anyInt(),anyInt());
    }

    @Test
    void upStatusApplicationsParticipationEventCurrentUser() {
        Event event = eventList.get(0).toBuilder()
                .state(State.PUBLISHED)
                .participantLimit(4)
                .confirmedRequests(0)
                .build();

        when(requestRepository.findAllById(anyList())).thenReturn(participationRequestList.stream()
                .map(pr -> pr.toBuilder()
                        .status(StatusRequest.PENDING).event(event).build()).collect(Collectors.toList()));
        when(eventsRepository.save(any())).thenReturn(event.toBuilder().confirmedRequests(3).build());
        when(requestRepository.saveAll(anyList())).thenReturn(participationRequestList.stream()
                .map(pr -> pr.toBuilder()
                        .status(StatusRequest.CONFIRMED).event(event).build()).collect(Collectors.toList()));
        EventRequestStatusUpdateResult result =
                userService.upStatusApplicationsParticipationEventCurrentUser(eventRequestStatusUpdateRequest,
                1,1,request);
        assertEquals(result,eventRequestStatusUpdateResult);

        verify(requestRepository).findAllById(anyList());
        verify(eventsRepository).save(any());
        verify(requestRepository).saveAll(anyList());
    }

    @Test
    void getInfoCurrentUserRequestsParticipateOtherPeopleEvents() {
        when(requestRepository.findByRequesterId(anyInt())).thenReturn(participationRequestList);
        userService.getInfoCurrentUserRequestsParticipateOtherPeopleEvents(1,request);
        verify(requestRepository).findByRequesterId(anyInt());
    }

    @Test
    void addRequestCurrentUserParticipateEvent() {
        Event event = eventList.get(0).toBuilder().state(State.PUBLISHED).build();

        when(eventsRepository.findById(anyInt())).thenReturn(
                Optional.of(event));
        when(eventsRepository.save(any())).thenReturn(event.toBuilder().confirmedRequests(1).build());
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(userList.get(0)));
        userService.addRequestCurrentUserParticipateEvent(2,1,request);
        verify(requestRepository).save(any());
    }

    @Test
    void upEventToParticipateCancel() {
        when(requestRepository.findById(anyInt())).thenReturn(Optional.of(participationRequestList.get(0)));
        userService.upEventToParticipateCancel(1,1,request);
        verify(requestRepository).findById(anyInt());
        verify(requestRepository).save(any());
    }
}