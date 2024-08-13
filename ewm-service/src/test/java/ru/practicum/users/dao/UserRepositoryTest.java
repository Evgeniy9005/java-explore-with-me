package ru.practicum.users.dao;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.practicum.users.model.User;
import ru.practicum.util.Util;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.practicum.data.Data.generationData;
import static ru.practicum.data.Data.printList;


@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private List<User> savedUser;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        savedUser = userRepository.saveAll(generationData(3,User.class));
        printList(savedUser,"sUs");
    }

    @AfterEach
    void end() {
        entityManager.createNativeQuery("ALTER TABLE USERS ALTER COLUMN ID RESTART WITH 1").executeUpdate();
       // entityManager.createNativeQuery("ALTER TABLE EVENTS ALTER COLUMN ID RESTART WITH 1").executeUpdate();
       // entityManager.createNativeQuery("ALTER TABLE PARTICIPATION_REQUEST ALTER COLUMN ID RESTART WITH 1").executeUpdate();
    }

    @Test
    void findAllByIdWithPageable() {
        userRepository.findAllByIdWithPageable(List.of(1,3), Util.page(0,10)).size();
    }
}