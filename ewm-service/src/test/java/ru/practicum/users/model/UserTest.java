package ru.practicum.users.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder().id(1).name("User").email("Email@mail.ru").build();
    }

    @Test
    void getId() {
        assertEquals(1,user.getId());
    }

    @Test
    void getName() {
        assertNotNull(user.getName());
    }

    @Test
    void getEmail() {
        assertNotNull(user.getEmail());
    }

    @Test
    void setId() {
        user.setId(2);
        assertEquals(2,user.getId());
    }

    @Test
    void setName() {
        user.setName("");

        assertEquals("",user.getName());
    }

    @Test
    void setEmail() {
        user.setEmail("e");
        assertEquals("e",user.getEmail());
    }
}