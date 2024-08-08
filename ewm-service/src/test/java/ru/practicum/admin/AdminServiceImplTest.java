package ru.practicum.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.category.dao.CategoryRepository;
import ru.practicum.category.dto.NewCategoryDto;
import ru.practicum.category.model.Category;
import ru.practicum.compilations.dao.CompilationRepository;
import ru.practicum.data.Controller;
import ru.practicum.events.dao.EventsRepository;
import ru.practicum.users.dao.UserRepository;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest extends Controller {

    private AdminService adminService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private EventsRepository eventsRepository;

    @Mock
    private CompilationRepository compilationRepository;

    @BeforeEach
    void setUp() {
        initUser(3);
        initCategory(3);
        initEventWithParam(5);
    }

    @Test
    void addNewCategory() {
       /* assertThrows(NotFoundException.class,() -> itemService.addItem(itemDtoList.get(0),1),
                "При добовлении вещи не найден пользователь под id = 1");*/

        //when(categoryRepository.save(any())).thenReturn(Optional.of(userList.get(0)));

        //adminService.addNewCategory(new NewCategoryDto("name",1));

        verify(categoryRepository).save(any(Category.class));
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