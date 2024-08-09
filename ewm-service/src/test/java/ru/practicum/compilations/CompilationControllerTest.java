package ru.practicum.compilations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import ru.practicum.admin.AdminController;
import ru.practicum.admin.AdminService;
import ru.practicum.category.dto.NewCategoryDto;
import ru.practicum.constants.State;
import ru.practicum.data.Controller;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CompilationController.class)
class CompilationControllerTest extends Controller {

    @MockBean
    protected CompilationService compilationService;

    @BeforeEach
    void setUp() {
        initCompilation(2);
    }

    @Test
    void getCompilations() throws Exception {
        when(compilationService.getCompilations(anyBoolean(),anyInt(),anyInt(),any()))
                .thenReturn(compilationDtoList);

        mvc.perform(get("/compilations")
                        .content(objectMapper.writeValueAsString(compilationDtoList))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                .param("pinned", "true")
                .param("from", "0")
                .param("size","2"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(compilationService).getCompilations(anyBoolean(),anyInt(),anyInt(),any());
    }

    @Test
    void getCompilation() throws Exception {
        when(compilationService.getCompilation(anyInt(),any()))
                .thenReturn(compilationDtoMap.get(1));

        mvc.perform(get("/compilations/1")
                        .content(objectMapper.writeValueAsString(compilationDtoMap.get(1)))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(compilationService).getCompilation(anyInt(),any());
    }
}