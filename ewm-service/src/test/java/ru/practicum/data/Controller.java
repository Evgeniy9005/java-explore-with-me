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
import ru.practicum.category.converter.CategoryMapper;
import ru.practicum.category.converter.CategoryMapperImpl;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.dto.NewCategoryDto;
import ru.practicum.category.model.Category;
import ru.practicum.client.StatsClient;
import ru.practicum.compilations.converter.CompilationMapper;
import ru.practicum.compilations.converter.CompilationMapperImpl;
import ru.practicum.compilations.model.Compilation;
import ru.practicum.events.EventsService;
import ru.practicum.events.coverter.EventsMapper;
import ru.practicum.events.coverter.EventsMapperImpl;
import ru.practicum.events.model.Event;
import ru.practicum.stats.Stats;
import ru.practicum.users.converter.UserMapper;
import ru.practicum.users.converter.UserMapperImpl;
import ru.practicum.users.model.User;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    protected static List<Category> categoryList;

    protected static CategoryMapper categoryMapper = new CategoryMapperImpl();

    protected static List<User> userList;

    protected UserMapper userMapper = new UserMapperImpl();

    protected static List<Event> eventList;

    protected EventsMapper eventsMapper = new EventsMapperImpl();

    protected static List<Compilation> compilationList;

    protected CompilationMapper compilationMapper = new CompilationMapperImpl();

    protected static Map<Integer,CategoryDto> getCategoryDtoMap(Integer createObjects) {

        categoryList = Data.generationData(createObjects,Category.class);
        Data.printList(categoryList,"ccc");

        return  categoryList.stream()
                .map(category -> categoryMapper.toCategoryDto(category))
                .collect(Collectors.toMap(c -> c.getId(),c -> c));
    }

    protected RequestPostProcessor request() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("192.168.0.1");
        return (RequestPostProcessor) request;
    }


}
