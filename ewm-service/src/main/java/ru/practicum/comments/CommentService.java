package ru.practicum.comments;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comments.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto addComment(String text, int eventId, int userId);


    CommentDto upComment(String text, int commentId, int userId);


    List<CommentDto> getCommentsEvent(int eventId);


    void deleteComment(int commentId, int userId);
}
