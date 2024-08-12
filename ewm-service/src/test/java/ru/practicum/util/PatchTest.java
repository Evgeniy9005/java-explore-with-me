package ru.practicum.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.data.Controller;

import static org.junit.jupiter.api.Assertions.*;

class PatchTest extends Controller {

    @BeforeEach
    void start() {
        initUser(2);
        initCategory(2);
        initEventWithParam(2);
    }

    @Test
    void patchEventAdmin() {
        Patch.patchEventAdmin(eventList.get(1),updateEventAdminRequestList.get(0),categoryList.get(0),2);
    }

    @Test
    void patchEventUser() {
    }

    @Test
    void patchCompilation() {
    }
}