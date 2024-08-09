package ru.practicum.admin;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import ru.practicum.admin.dto.UpdateEventAdminRequest;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.dto.NewCategoryDto;
import ru.practicum.constants.State;
import ru.practicum.data.Controller;
import ru.practicum.users.request.NewUserRequest;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminController.class)
class AdminControllerTest extends Controller {

    @MockBean
    protected AdminService adminService;

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
    void upEvent() throws Exception {
        when(adminService.upEvent(any(UpdateEventAdminRequest.class),anyInt(),any()))
                .thenReturn(eventFullDtoMap.get(1));

        mvc.perform(patch("/admin/events/1")
                        .content(objectMapper.writeValueAsString(updateEventAdminRequestList.get(0)))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(adminService).upEvent(any(),anyInt(),any());
    }

    @Test
    void getUsers() throws Exception {
        when(adminService.getUsers(any(),anyInt(),anyInt(),any()))
                .thenReturn(userDtoList);

        mvc.perform(get("/admin/users")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("ids", getUsersIdParam())
                        .param("from","0")
                        .param("size","100"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(adminService).getUsers(any(),anyInt(),anyInt(),any());

    }

    @Test
    void addNewUser() throws Exception {
        when(adminService.addNewUser(any(NewUserRequest.class),any())).thenReturn(userDtoMap.get(1));

        mvc.perform(post("/admin/users")
                        .content(objectMapper.writeValueAsString(newUserRequestList.get(0)))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
        verify(adminService).addNewUser(any(NewUserRequest.class),any());
    }

   /* @Test
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
    }*/
}