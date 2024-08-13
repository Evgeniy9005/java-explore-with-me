package ru.practicum.compilations.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.practicum.category.dao.CategoryRepository;
import ru.practicum.category.model.Category;
import ru.practicum.compilations.model.Compilation;
import ru.practicum.events.dao.EventsRepository;
import ru.practicum.events.model.Event;
import ru.practicum.users.dao.UserRepository;
import ru.practicum.users.model.User;
import ru.practicum.util.Util;
import java.util.List;

import static ru.practicum.data.Data.generationData;

@DataJpaTest
class CompilationRepositoryTest {

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

    private List<Compilation> savedCompilation;

    private Event event;

    @BeforeEach
    void setUp() {
        savedUser = userRepository.saveAll(generationData(2,User.class));
       /* Data.printList(savedUser,"sUs");
        savedCategory = categoryRepository.saveAll(categoryList);
        Data.printList(savedCategory,"sCs");

        event = eventsRepository.save(eventList.get(0).toBuilder()
                .initiator(savedUser.get(0))
                .category(savedCategory.get(0))
                .build());

        savedCompilation = compilationRepository.saveAll(compilationList);
        Data.printList(savedCompilation,"sCo");*/
    }

    @Test
    void findByPinned() {
     List<Compilation> compilations =
             compilationRepository.findByPinned(true, Util.page(0,10));

    }

}