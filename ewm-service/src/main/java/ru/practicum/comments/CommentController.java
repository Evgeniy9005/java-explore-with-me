package ru.practicum.comments;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comments.dto.CommentDto;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Validated
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments/{eventId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public CommentDto addComment(@RequestParam @NotBlank String text,
                                 @PathVariable @Positive int eventId,
                                 @RequestHeader("X-EWM-User-Id") @Positive int userId
    ) {
        return commentService.addComment(text,eventId,userId);
    }

    @PatchMapping("/comments/{commentId}")
    public CommentDto upComment(@RequestParam @NotBlank String text,
                                @PathVariable @Positive int commentId,
                                @RequestHeader("X-EWM-User-Id") @Positive int userId
                                ) {
        return commentService.upComment(text,commentId,userId);
    }

    @GetMapping("/comments/{eventId}")
    public List<CommentDto> getCommentsEvent(@PathVariable @Positive int eventId,
                                             @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                             @RequestParam(defaultValue = "10") @Positive int size
    ) {

        return commentService.getCommentsEvent(eventId,from,size);
    }

    @DeleteMapping("/comments/{commentId}/event/{eventId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable @Positive int commentId,
                              @RequestHeader("X-EWM-User-Id") @Positive int userId,
                              @PathVariable @Positive int eventId
    ) {
        commentService.deleteComment(commentId,userId,eventId);
    }

}
