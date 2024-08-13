package ru.practicum.events.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.constants.State;
import ru.practicum.events.model.Event;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventsRepository extends JpaRepository<Event,Integer>,CustomizedEventRepository {

   @Query("select e from Event e where e.initiator.id in(:users) " +
           "and e.state in(:states) " +
           "and e.category.id in(:categories) " +
           "and e.eventDate >= :rangeStart " +
           "and e.eventDate <= :rangeEnd")
   List<Event> getEvents(@Param("users") List<Integer> users,
                         @Param("states") List<State> states,
                         @Param("categories") List<Integer> categories,
                         @Param("rangeStart") LocalDateTime rangeStart,//дата и время не раньше которых должно произойти событие
                         @Param("rangeEnd") LocalDateTime rangeEnd,//дата и время не позже которых должно произойти событие
                         Pageable pageable);


   List<Event> findByInitiatorId(int userId,Pageable pageable);

   Optional<Event> findByInitiatorIdAndId(int userId, int eventId);

   Optional<Event> findByIdAndState(int eventId,State state); //для публичного эндпоинта можно вернуть только опубликованные события

}
