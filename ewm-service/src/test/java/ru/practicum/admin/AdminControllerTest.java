package ru.practicum.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.dto.NewCategoryDto;
import ru.practicum.data.Controller;
import ru.practicum.events.EventsController;
import ru.practicum.stats.Stats;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminController.class)
class AdminControllerTest extends Controller {



    @BeforeEach
    void setUp() {

    }

    @Test
    void addNewCategory() throws Exception {
        CategoryDto ca = new CategoryDto(1,"name");

        when(adminService.addNewCategory(any(NewCategoryDto.class),any())).thenReturn(ca);

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
    void deleteCategory() {

    }

    @Test
    void upCategory() {
    }

    @Test
    void getEvents() {
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