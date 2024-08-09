package ru.practicum.category.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.data.Data;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category category;
    @BeforeEach
    void setUp() {
        category = Data.<Category>generationData(1,Category.class).get(0);
    }

    @Test
    void getId() {
        assertNotNull(category);
        assertEquals(1,category.getId());
    }

    @Test
    void getName() {
        assertNotNull(category.getName());
    }

    @Test
    void setId() {
        category.setId(2);
        assertEquals(2,category.getId());
    }

    @Test
    void setName() {
        category.setName("n");
        assertEquals("n",category.getName());
    }

}