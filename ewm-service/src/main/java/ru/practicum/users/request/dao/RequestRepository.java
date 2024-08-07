package ru.practicum.users.request.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.users.request.model.ParticipationRequest;
import java.util.List;



public interface RequestRepository extends JpaRepository<ParticipationRequest,Integer> {

    //Получение информации о заявках текущего пользователя на участие в чужих событиях
    List<ParticipationRequest> findByRequesterId(int requesterId);

    //Получение информации о запросах на участие в событии текущего пользователя
    List<ParticipationRequest> findByEventInitiatorIdAndEventId(int initiatorId, int eventId);

   /* @Query("select count(pr.id) from ParticipationRequest pr where pr.event.id = ?1 and pr.status = ?2")
    int numberParticipants(int eventId,StatusRequest status);*/

   /* @Query("select new ru.practicum.users.request.model.EventIdAndParticipantId(e.id, count(pr.id), e.requestModeration) " +
            "from ParticipationRequest pr " +
            "join Event e on e.id = pr.event.id " +
            "where e.id = :eventId and pr.status = :status " + //and pr.id in(:participationRequestIds) " +
            "group by e.id ")
    EventIdAndParticipantId numberEventsAndNumberParticipants(
            @Param("eventId") int eventId,
            //@Param("participationRequestIds") List<Integer> participationRequestIds,
            @Param("status") StatusRequest status
            );*/
}
