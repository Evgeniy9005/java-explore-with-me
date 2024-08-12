package ru.practicum.events.dao;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.practicum.category.dao.CategoryRepository;
import ru.practicum.category.model.Category;
import ru.practicum.compilations.dao.CompilationRepository;
import ru.practicum.constants.State;
import ru.practicum.events.model.Event;
import ru.practicum.users.dao.UserRepository;
import ru.practicum.users.model.User;
import ru.practicum.util.Util;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.practicum.data.Data.generationData;
import static ru.practicum.data.Data.printList;

@DataJpaTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EventsRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompilationRepository compilationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EventsRepository eventsRepository;

    private List<User> savedUser;

    private List<Category> savedCategory;

    private List<Event> savedEvent;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        savedUser = userRepository.saveAll(generationData(2,User.class));
        printList(savedUser,"sUs");
        savedCategory = categoryRepository.saveAll(generationData(2,Category.class));
        printList(savedCategory,"sCs");
        savedEvent = eventsRepository.saveAll(
                generationData(3,Event.class,savedUser.get(0),savedCategory.get(0)));
        printList(savedEvent,"sEs");
    }

    @AfterEach
    void end() {
        entityManager.createNativeQuery("ALTER TABLE USERS ALTER COLUMN ID RESTART WITH 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE EVENTS ALTER COLUMN ID RESTART WITH 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE CATEGORY ALTER COLUMN ID RESTART WITH 1;").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE PARTICIPATION_REQUEST ALTER COLUMN ID RESTART WITH 1").executeUpdate();
    }

    @Test
    @Order(1)
    void getEvents() {
        assertEquals(eventsRepository.getEvents(List.of(1,2),
                List.of(State.PENDING),
                List.of(1,2),
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1),
                Util.page(0,10)),savedEvent);
    }

    @Test
    @Order(2)
    void findByInitiatorId() {
        eventsRepository.findByInitiatorId(1,Util.page(0,10));
    }

    @Test
    @Order(3)
    void findByInitiatorIdAndId() {
        eventsRepository.findByInitiatorIdAndId(1,1);
    }

    @Test
    @Order(4)
    void findByIdAndState() {
        eventsRepository.findByIdAndState(1,State.PENDING);
    }
}