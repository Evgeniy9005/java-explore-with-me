package ru.practicum.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import ru.practicum.admin.dto.UpdateEventAdminRequest;
import ru.practicum.category.converter.CategoryMapper;
import ru.practicum.category.converter.CategoryMapperImpl;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.model.Category;
import ru.practicum.client.StatsClient;
import ru.practicum.compilations.converter.CompilationMapper;
import ru.practicum.compilations.converter.CompilationMapperImpl;
import ru.practicum.compilations.dto.CompilationDto;
import ru.practicum.compilations.dto.NewCompilationDto;
import ru.practicum.compilations.dto.UpdateCompilationRequest;
import ru.practicum.compilations.model.Compilation;
import ru.practicum.constants.State;
import ru.practicum.constants.StatusRequest;
import ru.practicum.events.coverter.EventsMapper;
import ru.practicum.events.coverter.EventsMapperImpl;
import ru.practicum.events.dto.EventFullDto;
import ru.practicum.events.dto.EventShortDto;
import ru.practicum.events.dto.NewEventDto;
import ru.practicum.events.model.Event;
import ru.practicum.users.converter.UserMapper;
import ru.practicum.users.converter.UserMapperImpl;
import ru.practicum.users.dto.UpdateEventUserRequest;
import ru.practicum.users.dto.UserDto;
import ru.practicum.users.model.User;
import ru.practicum.users.request.EventRequestStatusUpdateRequest;
import ru.practicum.users.request.EventRequestStatusUpdateResult;
import ru.practicum.users.request.NewUserRequest;
import ru.practicum.users.request.converter.RequestMapper;
import ru.practicum.users.request.converter.RequestMapperImpl;
import ru.practicum.users.request.dto.ParticipationRequestDto;
import ru.practicum.users.request.model.ParticipationRequest;
import ru.practicum.util.Util;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import static ru.practicum.data.Data.generationData;
import static ru.practicum.data.Data.printList;

public class Controller {

    @Autowired
    protected ObjectMapper objectMapper;

    @Mock
    protected StatsClient statsClient;

    @Mock
    protected HttpServletRequest request;

    @Autowired
    protected MockMvc mvc;

    protected List<Category> categoryList;

    private List<Integer> categoryIdList;

    protected List<CategoryDto> categoryDtoList = new ArrayList<>();

    protected Map<Integer,CategoryDto> categoryDtoMap;

    protected CategoryMapper categoryMapper = new CategoryMapperImpl();

    protected List<User> userList;

    private List<Integer> userIdList;

    protected List<UserDto> userDtoList = new ArrayList<>();

    protected Map<Integer, UserDto> userDtoMap;

    protected List<NewUserRequest> newUserRequestList;

    protected UserMapper userMapper = new UserMapperImpl();

    protected List<Event> eventList;

    protected List<Integer> eventIdList;

    protected NewEventDto newEventDto;

    protected List<EventFullDto> eventFullDtoList = new ArrayList<>();

    protected List<EventShortDto> eventShortDtoList = new ArrayList<>();

    protected Map<Integer,EventFullDto> eventFullDtoMap;

    protected Map<Integer,EventShortDto> eventShortDtoMap;

    protected EventsMapper eventsMapper = new EventsMapperImpl();

    protected List<UpdateEventAdminRequest> updateEventAdminRequestList;

    protected UpdateEventUserRequest updateEventUserRequest;

    protected List<ParticipationRequest> participationRequestList;

    protected List<ParticipationRequestDto> participationRequestDtoList = new ArrayList<>();

    protected List<ParticipationRequestDto> prDtoConfirmedList;

    protected List<ParticipationRequestDto> prDtoRejectedList;

    protected Map<Integer,ParticipationRequestDto> participationRequestDtoMap;

    protected RequestMapper requestMapper = new RequestMapperImpl();

    protected EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest;

    protected EventRequestStatusUpdateResult eventRequestStatusUpdateResult;

    protected List<Compilation> compilationList;

    protected List<CompilationDto> compilationDtoList = new ArrayList<>();

    protected Map<Integer,CompilationDto> compilationDtoMap;

    protected CompilationMapper compilationMapper = new CompilationMapperImpl();

    protected NewCompilationDto newCompilationDto;

    protected UpdateCompilationRequest updateCompilationRequest;

    protected List<State> stateList = new ArrayList<>();

    protected void initCategory(Integer createObjects) {
        categoryList = generationData(createObjects,Category.class);
        printList(categoryList,"ccc");

        categoryDtoMap = categoryList.stream()
                .map(category -> categoryMapper.toCategoryDto(category))
                .collect(Collectors.toMap(c -> c.getId(),c -> c));

        categoryDtoList.addAll(categoryDtoMap.values());
    }

    protected void initUser(Integer createObjects) {
        userList = generationData(createObjects,User.class);
        printList(userList);

        userDtoMap = userList.stream()
                .map(user -> userMapper.toUserDto(user))
                .collect(Collectors.toMap(UserDto::getId,u -> u));

        userDtoList.addAll(userDtoMap.values());

        newUserRequestList = generationData(createObjects,NewUserRequest.class);
    }

    protected void initEventWithParam(Integer createObjects) {
        eventList = generationData(createObjects,Event.class,userList.get(0),categoryList.get(0));
        printList(eventList);

        eventFullDtoMap = eventList.stream()
                .map(event -> eventsMapper.toEventFullDto(event))
                .collect(Collectors.toMap(EventFullDto::getId,eventFullDto -> eventFullDto));

        eventShortDtoMap = eventList.stream()
                .map(event -> eventsMapper.toEventShortDto(event))
                .collect(Collectors.toMap(EventShortDto::getId,eventShortDto -> eventShortDto));

        eventFullDtoList.addAll(eventFullDtoMap.values());
        eventShortDtoList.addAll(eventShortDtoMap.values());

        updateEventAdminRequestList = generationData(createObjects,UpdateEventAdminRequest.class);

        EventFullDto e = eventFullDtoMap.get(1);
        newEventDto = NewEventDto.builder()
                .annotation(e.getAnnotation())
                .description(e.getDescription())
                .category(e.getCategory().getId())
                .location(e.getLocation())
                .paid(e.getPaid())
                .requestModeration(e.getRequestModeration())
                .participantLimit(e.getParticipantLimit())
                .eventDate(e.getEventDate())
                .title(e.getTitle())
                .build();

        updateEventUserRequest = UpdateEventUserRequest.builder()
                .annotation(e.getAnnotation())
                .description(e.getDescription())
                .category(e.getCategory().getId())
                .location(e.getLocation())
                .paid(e.getPaid())
                .requestModeration(e.getRequestModeration())
                .participantLimit(e.getParticipantLimit())
                .eventDate(e.getEventDate())
                .title(e.getTitle())
                .stateAction("PUBLISH_EVENT")
                .build();
    }

    protected void initParticipationRequest(Integer createObjects) {
        participationRequestList = generationData(createObjects,
                ParticipationRequest.class,
                eventList.get(0),
                userList.get(0));
        printList(participationRequestList);

        participationRequestDtoMap = participationRequestList.stream()
                .map(pr -> requestMapper.toDto(pr))
                .collect(Collectors.toMap(ParticipationRequestDto::getId,pr -> pr));

        participationRequestDtoList.addAll(participationRequestDtoMap.values());

        prDtoConfirmedList = participationRequestDtoList.stream()
                .map(pr -> pr.toBuilder().status(StatusRequest.CONFIRMED.toString()).build())
                .collect(Collectors.toList());

        prDtoRejectedList = participationRequestDtoList.stream()
                .map(pr -> pr.toBuilder().status(StatusRequest.REJECTED.toString()).build())
                .collect(Collectors.toList());

        eventRequestStatusUpdateRequest = new EventRequestStatusUpdateRequest(getEventIdList(),StatusRequest.CONFIRMED);

        eventRequestStatusUpdateResult = new EventRequestStatusUpdateResult(prDtoConfirmedList,new ArrayList<>());
    }

    protected void initCompilation(Integer createObjects) {
        compilationList = generationData(createObjects,Compilation.class,getEventIdList());
        printList(compilationList,"cTc");

        compilationDtoMap = compilationList.stream()
                .map(compilation -> compilationMapper.toCompilationDto(compilation,
                        eventList == null ? new ArrayList<>() : eventList))
                .collect(Collectors.toMap(c -> c.getId(),c -> c));

        compilationDtoList.addAll(compilationDtoMap.values());

        newCompilationDto = NewCompilationDto.builder()
                .events(getEventIdList())
                .pinned(true)
                .title("Подборка")
                .build();

        updateCompilationRequest = UpdateCompilationRequest.builder()
                .events(getEventIdList())
                .pinned(true)
                .title("Обновленная подборка")
                .build();
    }

    protected String getUsersIdParam() {
        return String.valueOf(userList.stream().map(User::getId)
                .collect(Collectors.toList()))
                .replace("[","").replace("]","");
    }

    protected String getCategoryIdParam() throws Exception {
        return objectMapper.writeValueAsString(
                categoryList.stream().map(Category::getId).collect(Collectors.toList())
        );
    }

    protected String toJson(Object o) throws Exception {
        return objectMapper.writeValueAsString(o);
    }

    protected List<CategoryDto> getCategoryDtoList() {
        return categoryDtoList;
    }

    protected Map<Integer,CategoryDto> getCategoryDtoMap() {
        return categoryDtoMap;
    }

    protected RequestPostProcessor request() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("192.168.0.1");
        return (RequestPostProcessor) request;
    }

    protected List<Integer> getUserIdList() {
        userIdList = userList.stream().map(User::getId).collect(Collectors.toList());
        return userIdList;
    }

    protected List<Integer> getCategoryIdList() {
        categoryIdList = categoryList.stream().map(Category::getId).collect(Collectors.toList());
    return categoryIdList;
    }

    protected List<State> getStateList() {
        stateList.add(State.PENDING);
        stateList.add(State.PUBLISHED);
        stateList.add(State.CANCELED);
        return stateList;
    }

    protected List<Integer> getEventIdList() {
        if (eventList == null) {
            System.out.println("Пустой список для подборки");
            return new ArrayList<>();
        }
        eventIdList = eventList.stream().map(Event::getId).collect(Collectors.toList());
        return eventIdList;
    }

    protected String getStart() {
        return LocalDateTime.now().format(Util.getFormatter());
    }

    protected String getEnd() {
        return LocalDateTime.now().plusMonths(1).format(Util.getFormatter());
    }
}
