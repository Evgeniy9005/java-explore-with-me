package ru.practicum.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import ru.practicum.admin.AdminService;
import ru.practicum.admin.dto.UpdateEventAdminRequest;
import ru.practicum.category.converter.CategoryMapper;
import ru.practicum.category.converter.CategoryMapperImpl;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.dto.NewCategoryDto;
import ru.practicum.category.model.Category;
import ru.practicum.client.StatsClient;
import ru.practicum.compilations.converter.CompilationMapper;
import ru.practicum.compilations.converter.CompilationMapperImpl;
import ru.practicum.compilations.dto.CompilationDto;
import ru.practicum.compilations.model.Compilation;
import ru.practicum.events.EventsService;
import ru.practicum.events.coverter.EventsMapper;
import ru.practicum.events.coverter.EventsMapperImpl;
import ru.practicum.events.dto.EventFullDto;
import ru.practicum.events.dto.EventShortDto;
import ru.practicum.events.model.Event;
import ru.practicum.stats.Stats;
import ru.practicum.users.converter.UserMapper;
import ru.practicum.users.converter.UserMapperImpl;
import ru.practicum.users.dto.UserDto;
import ru.practicum.users.model.User;
import ru.practicum.users.request.NewUserRequest;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.practicum.data.Data.generationData;
import static ru.practicum.data.Data.printList;

public class Controller {

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected EventsService eventsService;

    @MockBean
    protected AdminService adminService;

    @Mock
    protected StatsClient statsClient;

    @Autowired
    protected MockMvc mvc;

    protected List<Category> categoryList;

    protected List<CategoryDto> categoryDtoList = new ArrayList<>();

    protected Map<Integer,CategoryDto> categoryDtoMap;

    protected CategoryMapper categoryMapper = new CategoryMapperImpl();

    protected List<User> userList;

    protected List<UserDto> userDtoList = new ArrayList<>();

    protected Map<Integer, UserDto> userDtoMap;

    protected List<NewUserRequest> newUserRequestList;

    protected UserMapper userMapper = new UserMapperImpl();

    protected List<Event> eventList;

    protected List<EventFullDto> eventFullDtoList = new ArrayList<>();

    protected List<EventShortDto> eventShortDtoList = new ArrayList<>();

    protected Map<Integer,EventFullDto> eventFullDtoMap;

    protected Map<Integer,EventShortDto> eventShortDtoMap;

    protected EventsMapper eventsMapper = new EventsMapperImpl();

    protected List<UpdateEventAdminRequest> updateEventAdminRequestList;

    protected List<Compilation> compilationList;

    protected List<CompilationDto> compilationDtoList = new ArrayList<>();

    protected Map<Integer,CompilationDto> compilationDtoMap;

    protected CompilationMapper compilationMapper = new CompilationMapperImpl();

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
    }

    protected String getUsersIdParam() throws Exception {
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


}
