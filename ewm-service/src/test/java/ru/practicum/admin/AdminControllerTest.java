package ru.practicum.admin;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.dto.NewCategoryDto;
import ru.practicum.constants.State;
import ru.practicum.data.Controller;
import ru.practicum.events.EventsController;
import ru.practicum.stats.Stats;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminController.class)
class AdminControllerTest extends Controller {


     static Map<Integer,CategoryDto> categoryDtoMap;

    @BeforeEach
     void setUp() {
        initUser(2);
        initCategory(2);
        initEventWithParam(5);
    }

    @Test
    void addNewCategory() throws Exception {
        when(adminService.addNewCategory(any(NewCategoryDto.class),any())).thenReturn(categoryDtoMap.get(1));

            mvc.perform(post("/admin/categories")
                            .content(objectMapper.writeValueAsString(new NewCategoryDto("name",1)))
                            .characterEncoding(StandardCharsets.UTF_8)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated());
        verify(adminService).addNewCategory(any(),any());
    }

    @Test
    void deleteCategory() throws Exception {
        doNothing().when(adminService).deleteCategory(anyInt(),any(HttpServletRequest.class));
        mvc.perform(delete("/admin/categories/1"))
                .andExpect(status().isNoContent());
        verify(adminService).deleteCategory(anyInt(),any(HttpServletRequest.class));

        mvc.perform(delete("/admin/categories/-1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void upCategory() throws Exception {
        when(adminService.upCategory(any(CategoryDto.class),anyInt(),any())).thenReturn(categoryDtoMap.get(1));

        mvc.perform(patch("/admin/categories/1")
                        .content(objectMapper.writeValueAsString(categoryDtoMap.get(1)))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(adminService).upCategory(any(),anyInt(),any());
    }

    @Test
    void getEvents() throws Exception {

        when(adminService.getEvents(any(),any(),any(),anyString(),anyString(),anyInt(),anyInt(),any()))
                .thenReturn(eventFullDtoList);

        mvc.perform(get("/admin/events")
                        .content(objectMapper.writeValueAsString(eventFullDtoList))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                .param("users", getUsersIdParam())
                .param("states",State.PUBLISHED.toString())
                .param("categories","1,2")
                .param("paid","true")
                .param("rangeStart","2024-11-30 15:10:05")
                .param("rangeEnd","2024-12-31 15:10:05")
                .param("from","0")
                .param("size","100"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(adminService).getEvents(any(),any(),any(),anyString(),anyString(),anyInt(),anyInt(),any());
    }

    @Test
    void upEvent() {
    }

    @Test
    void getUsers() {
    }

    @Test
    void addNewUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void addNewCompilation() {
    }

    @Test
    void deleteCompilation() {
    }

    @Test
    void upCompilation() {
    }
}