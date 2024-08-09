package ru.practicum.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import ru.practicum.category.dao.CategoryRepository;
import ru.practicum.category.dto.NewCategoryDto;
import ru.practicum.category.model.Category;
import ru.practicum.compilations.dao.CompilationRepository;
import ru.practicum.compilations.model.Compilation;
import ru.practicum.constants.State;
import ru.practicum.data.Controller;
import ru.practicum.events.dao.EventsRepository;
import ru.practicum.events.model.Event;
import ru.practicum.users.dao.UserRepository;
import ru.practicum.users.model.User;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
        objectMapper = new ObjectMapper();
        adminService = new AdminServiceImpl(categoryRepository,
                categoryMapper,
                userRepository,
                userMapper,
                eventsRepository,
                eventsMapper,
                compilationRepository,
                compilationMapper,
                objectMapper);

        initUser(3);
        initCategory(3);
        initEventWithParam(5);
        initCompilation(2);
    }

    @Test
    void addNewCategory() {
        adminService.addNewCategory(new NewCategoryDto("name",1), request);

        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void deleteCategory() {
        adminService.deleteCategory(1,request);

        verify(categoryRepository).deleteById(1);
    }

    @Test
    void upCategory() {
        adminService.upCategory(categoryDtoMap.get(1),1,request);
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void getEvents() {
        adminService.getEvents(getUserIdList(),
                getStateList(),
                getCategoryIdList(),
                getStart(),
                getEnd(),
                0,
                10,
                request);

        verify(eventsRepository).getEvents(anyList(),
                anyList(),
                anyList(),
                any(LocalDateTime.class),
                any(LocalDateTime.class),
                any(Pageable.class));
    }

    @Test
    void upEvent() {
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(categoryList.get(0)));
        when(eventsRepository.findById(anyInt()))
                .thenReturn(Optional.of(eventList.get(0).toBuilder().state(State.PENDING).build()));
        adminService.upEvent(updateEventAdminRequestList.get(0),1,request);
        verify(eventsRepository).save(any(Event.class));
    }

    @Test
    void getUsers() {
        adminService.getUsers(getUserIdList(),0,10,request);
        verify(userRepository).findAllByIdWithPageable(anyList(),any(Pageable.class));
    }

    @Test
    void addNewUser() {
        adminService.addNewUser(newUserRequestList.get(0),request);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void deleteUser() {
        adminService.deleteUser(1,request);
        verify(userRepository).deleteById(1);
    }

    @Test
    void addNewCompilation() {
        adminService.addNewCompilation(newCompilationDto,request);
        verify(compilationRepository).save(any(Compilation.class));
    }

    @Test
    void deleteCompilation() {
        adminService.deleteCompilation(1,request);
        verify(compilationRepository).deleteById(1);
    }

    @Test
    void upCompilation() {
        when()
        adminService.upCompilation(updateCompilationRequest,1,request);

    }
}