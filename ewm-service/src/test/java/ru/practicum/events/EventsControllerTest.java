package ru.practicum.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.client.StatsClient;
import ru.practicum.data.Data;
import ru.practicum.events.dto.EventFullDto;
import ru.practicum.events.dto.EventShortDto;
import ru.practicum.events.dto.NewEventDto;
import ru.practicum.events.model.Location;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = EventsController.class)
class EventsControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EventsService eventsService;

    @Mock
    private StatsClient statsClient;

    @Autowired
    private MockMvc mvc;

    private NewEventDto newEventDto;

    private List<EventShortDto> eventShortDtoList;

    private List<EventFullDto> eventFullDtoList;

    @BeforeEach
    void start() {
    newEventDto = NewEventDto.builder()
            .annotation("Краткое описание события")
            .paid(false)
            .location(new Location(53.1F,54.2F))
            .eventDate("2024-12-31 15:10:05")
            .description("Полное описание события")
            .participantLimit(0)
            .requestModeration(true)
            .title("Событие")
            .build();
    eventShortDtoList = Data.generationData(2,EventShortDto.class);
    Data.printList(eventShortDtoList,">>>");

    eventFullDtoList = Data.generationData(2,EventFullDto.class);
    Data.printList(eventFullDtoList,"<<<");
    }

    @Test
    void getEvents() throws Exception {
            mvc.perform(get("/events")
                            .with(request -> {
                                request.setRemoteAddr("192.168.0.1");
                                return request;
                            })
                            .content(objectMapper.writeValueAsString(eventShortDtoList))
                            .characterEncoding(StandardCharsets.UTF_8)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .param("text","какой-то текст")
                            .param("categories","1")
                            .param("paid","true")
                            .param("rangeStart","2024-11-30 15:10:05")
                            .param("rangeEnd","2024-12-31 15:10:05")
                            .param("onlyAvailable","true")
                            .param("sort","EVENT_DATE"))
                    .andDo(print())
                    .andExpect(status().isOk());
    }

    @Test
    void getEvent() throws Exception {
            when(statsClient.put(any())).thenReturn("Ответ");
            mvc.perform(get("/events/1")
                            .with(request -> {
                                request.setRemoteAddr("192.168.0.1");
                                return request;
                            })
                            .content(objectMapper.writeValueAsString(eventFullDtoList.get(0)))
                            .characterEncoding(StandardCharsets.UTF_8)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
    }
}