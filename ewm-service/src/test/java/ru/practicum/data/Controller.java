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
import ru.practicum.category.dto.NewCategoryDto;
import ru.practicum.client.StatsClient;
import ru.practicum.events.EventsService;
import ru.practicum.stats.Stats;

import java.nio.charset.StandardCharsets;

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

   // protected MockedStatic<Stats> theMock;

   /* public void init() {
        try (MockedStatic<Stats> theMock = Mockito.mockStatic(Stats.class)) {

        } catch (Exception e) {
            System.out.println(e);
        }
    }*/

    protected RequestPostProcessor request(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("192.168.0.1");
        return (RequestPostProcessor) request;
    }
}
