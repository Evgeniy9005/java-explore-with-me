package ru.practicum.comments;

import ru.practicum.comments.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto addComment(String text, int eventId, int userId);


    CommentDto upComment(String text, int commentId, int userId);


    List<CommentDto> getCommentsEvent(int eventId, int from, int size);


    void deleteComment(int commentId, int userId, int eventId);
}
