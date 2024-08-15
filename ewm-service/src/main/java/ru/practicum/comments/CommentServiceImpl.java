package ru.practicum.comments;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.BadRequestException;
import ru.practicum.NotFoundException;
import ru.practicum.comments.converter.CommentMapper;
import ru.practicum.comments.dao.CommentRepository;
import ru.practicum.comments.dto.CommentDto;
import ru.practicum.comments.model.Comment;
import ru.practicum.constants.State;
import ru.practicum.events.dao.EventsRepository;
import ru.practicum.events.model.Event;
import ru.practicum.users.dao.UserRepository;
import ru.practicum.users.model.User;
import ru.practicum.util.Util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;

    public final EventsRepository eventsRepository;

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    @Override
    @Transactional
    public CommentDto addComment(String text, int eventId, int userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Не найден пользователь # при добавлении комментария!",userId));

        Event event = eventsRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Не найдено событие # при добавлении комментария!",eventId));

        State state = event.getState();

        if (!state.equals(State.PUBLISHED)) {
            throw new BadRequestException("Нельзя оставить комментарий на не опубликованное событие #",eventId);
        }

        Comment comment = Comment.builder()
                .text(text)
                .author(user)
                .event(event)
                .created(LocalDateTime.now())
                .build();

        Comment newCom = commentRepository.save(comment);

        CommentDto commentDto = commentMapper.toCommentDto(newCom);

        log.info("Добавлен комментарий {} к событию {} пользователем {}",commentDto,eventId,userId);

        return commentDto;
    }

    @Override
    @Transactional
    public CommentDto upComment(String text, int commentId, int userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Не найден пользователь # при обновлении комментария!",userId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Не найден комментарий # при обновлении комментария!",commentId));

        int eventId = comment.getEvent().getId();

        if (comment.getAuthor().getId() != userId) {
            throw new BadRequestException("Пользователь # не делал комментарий # к событию #!",userId,commentId,eventId);
        }

        Comment newCom = commentRepository.save(comment.toBuilder().text(text).build());

        CommentDto commentDto = commentMapper.toCommentDto(newCom);

        log.info("Обновлен комментарий {} к событию {} пользователем {}",commentDto,eventId,userId);

        return commentDto;
    }

    @Override
    public List<CommentDto> getCommentsEvent(int eventId, int from, int size) {

        if (!eventsRepository.existsById(eventId)) {
            throw new NotFoundException("Не найдено событие # при получении его комментариев!",eventId);
        }

        Sort sort = Sort.by(Sort.Direction.ASC,"id");

        List<Comment> commentList = commentRepository.findByEventId(eventId, Util.page(from,size,sort));

        List<CommentDto> commentDtoList = commentList.stream()
                .map(comment -> commentMapper.toCommentDto(comment))
                .collect(Collectors.toList());

        log.info("Получены  комментарии на событие в размере {}!",commentDtoList.size());

        return commentDtoList;
    }

    @Override
    public void deleteComment(int commentId, int userId, int eventId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Не найден комментарий # при удалении комментария!",commentId));

        if (comment.getEvent().getId() != eventId) {
            throw new BadRequestException("Комментария # на событие # не существует!",commentId,eventId);
        }

        if (comment.getAuthor().getId() != userId) {
            throw new BadRequestException("Пользователь # не делал комментарий # к событию #!",userId,commentId,eventId);
        }

        commentRepository.deleteById(commentId);
        log.info("Удален комментарий {}",commentId);
    }
}
