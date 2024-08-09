package ru.practicum.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import ru.practicum.data.Controller;
import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CategoryController.class)
class CategoryControllerTest extends Controller {

    @MockBean
    private CategoryService categoryService;

    @BeforeEach
    void start () {
    initCategory(3);
    }

    @Test
    void getCategories() throws Exception {
        when(categoryService.getCategories(anyInt(),anyInt(),any()))
                .thenReturn(categoryDtoList);

        mvc.perform(get("/categories")
                        .content(objectMapper.writeValueAsString(categoryDtoList))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("from", "0")
                        .param("size", "5"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(categoryService).getCategories(anyInt(),anyInt(),any());
    }

    @Test
    void getCategory() throws Exception {
        when(categoryService.getCategory(anyInt(),any()))
                .thenReturn(categoryDtoMap.get(1));

        mvc.perform(get("/categories/1")
                        .content(objectMapper.writeValueAsString(categoryDtoMap.get(1)))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(categoryService).getCategory(anyInt(),any());
    }
}