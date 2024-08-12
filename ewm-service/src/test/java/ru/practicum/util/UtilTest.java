package ru.practicum.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void page() {
        assertNotNull(Util.page(0,10));
    }

    @Test
    void getFormatter() {
        assertNotNull(Util.getFormatter());
    }

    @Test
    void getDate() {
        assertNotNull(Util.getDate(LocalDateTime.now().format(Util.getFormatter())));
    }

    @Test
    void getDateStart() {
        assertNotNull(Util.getDateStart(LocalDateTime.now().format(Util.getFormatter())));
    }

    @Test
    void getDateEnd() {
        assertNotNull(Util.getDateStart(LocalDateTime.now().format(Util.getFormatter())));
        assertNotNull(Util.getDateEnd(LocalDateTime.now().format(Util.getFormatter())));
    }
}