package ru.practicum.events;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.constants.SortEvents;
import ru.practicum.data.Controller;
import ru.practicum.events.dao.EventsRepository;
import ru.practicum.stats.Stats;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EventsServiceImplTest extends Controller {

    private EventsService eventsService;

    @Mock
    private EventsRepository eventsRepository;

    @BeforeEach
    void setUp() {
        eventsService = new EventsServiceImpl(eventsRepository,eventsMapper);
        initUser(1);
        initCategory(1);
        initEventWithParam(3);
    }

    @Test
    void getEvents() {
        try (MockedStatic<Stats> theMock = Mockito.mockStatic(Stats.class)) {
            theMock.when(Stats::getStatsClient).thenReturn(statsClient);

            when(statsClient.put(any())).thenReturn("Ответ");
            eventsService.getEvents("text",
                    getCategoryIdList(),
                    true,
                    getStart(),
                    getEnd(),
                    true,
                    SortEvents.EVENT_DATE,
                    0,
                    10,
                    request);

            verify(eventsRepository).searchE(any(),any(),anyInt(),anyInt());
            verify(statsClient).put(any());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    void getEvent() {
        try (MockedStatic<Stats> theMock = Mockito.mockStatic(Stats.class)) {
            theMock.when(Stats::getStatsClient).thenReturn(statsClient);

            when(statsClient.put(any())).thenReturn("Ответ");

            eventsService.getEvent(1, request);

            verify(eventsRepository).findById(anyInt());
            verify(statsClient).put(any());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}