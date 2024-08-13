package ru.practicum.events.dao;

import ru.practicum.events.model.Event;
import java.util.List;
import java.util.Map;

public interface CustomizedEventRepository {

    List<Event> searchE(String query,
                        Map<String,Object> param,
                        int from,
                        int size);
}
