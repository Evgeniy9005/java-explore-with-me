package ru.practicum.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultDataTest {
    private DefaultData defaultData;

    @BeforeEach
    void setUp() {
        defaultData = new DefaultData();
    }

    @Test
    void getIdList() {
        assertNotNull(defaultData.getIdList());
    }

    @Test
    void getStateList() {
        assertNotNull(defaultData.getStateList());
    }
}