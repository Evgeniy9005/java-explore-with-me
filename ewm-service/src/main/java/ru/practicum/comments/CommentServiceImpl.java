package ru.practicum.comments;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.NotFoundException;
import ru.practicum.comments.dao.CommentRepository;
import ru.practicum.comments.dto.CommentDto;
import ru.practicum.events.dao.EventsRepository;
import ru.practicum.events.model.Event;
import ru.practicum.users.dao.UserRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;

    public final EventsRepository eventsRepository;

    private final CommentRepository commentRepository;


    public CommentDto addComment(String text, int eventId, int userId) {


        Event event = eventsRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Не найдено событие при добалении события"));


        return null;
    }


    public CommentDto upComment(String text, int commentId, int userId) {
        return null;
    }


    public List<CommentDto> getCommentsEvent(int eventId) {
        return null;
    }


    public void deleteComment(int commentId, int userId) {

    }
}
