package ru.practicum.events.dao;

import lombok.RequiredArgsConstructor;
import ru.practicum.events.model.Event;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
public class CustomizedEventRepositoryImpl implements CustomizedEventRepository {

    private final EntityManager em;


    @Override
    public List<Event> searchE(String query,
                               Map<String,Object> param,
                               int from,
                               int size
    ) {

        TypedQuery<Event> typedQuery = em.createQuery(query, Event.class);

        param.entrySet().stream().forEach(e -> typedQuery.setParameter(e.getKey(),e.getValue()));

        typedQuery.setFirstResult(from)
                .setMaxResults(size);

        return typedQuery.getResultList();
    }
}
