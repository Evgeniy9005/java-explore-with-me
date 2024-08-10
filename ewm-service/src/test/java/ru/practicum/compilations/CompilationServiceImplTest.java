package ru.practicum.compilations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import ru.practicum.compilations.dao.CompilationRepository;
import ru.practicum.data.Controller;
import ru.practicum.events.dao.EventsRepository;
import ru.practicum.util.Util;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompilationServiceImplTest extends Controller {

    private CompilationService compilationService;

    @Mock
    private CompilationRepository compilationRepository;

    @Mock
    private EventsRepository eventsRepository;


    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        compilationService = new CompilationServiceImpl(compilationRepository,
                compilationMapper,
                eventsRepository,
                objectMapper);

        initUser(1);
        initCategory(1);
        initEventWithParam(2);
        initCompilation(2);
    }

    @Test
    void getCompilations() {

        when(compilationRepository.findByPinned(anyBoolean(),any())).thenReturn(compilationList);
        when(eventsRepository.findAllById(any())).thenReturn(eventList);

        compilationService.getCompilations(true,0,2,request);

        verify(compilationRepository).findByPinned(anyBoolean(),any());
        verify(eventsRepository).findAllById(any());
    }

    @Test
    void getCompilation() {
        when(compilationRepository.findById(anyInt())).thenReturn(Optional.of(compilationList.get(0)));
        when(eventsRepository.findAllById(any())).thenReturn(eventList);

        compilationService.getCompilation(1,request);

        verify(compilationRepository).findById(anyInt());
        verify(eventsRepository).findAllById(any());
    }
}