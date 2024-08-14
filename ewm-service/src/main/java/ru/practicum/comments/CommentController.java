package ru.practicum.comments;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comments.dto.CommentDto;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Validated
public class CommentController {

    @PostMapping("/comments/{eventId}")
    public CommentDto addComment(@NotBlank String text,
                                 @PathVariable @Positive int eventId,
                                 @RequestHeader("X-EWM-User-Id") @Positive int userId
    ) {
        return null;
    };

    @PatchMapping("/comments/{commentId}")
    public CommentDto upComment(@NotBlank String text,
                                @PathVariable @Positive int commentId,
                                @RequestHeader("X-EWM-User-Id") @Positive int userId
                                ) {
        return null;
    }

    @GetMapping("/comments/{eventId}")
    public List<CommentDto> getCommentsEvent(@PathVariable @Positive int eventId) {

        return null;
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable @Positive int commentId,
                              @RequestHeader("X-EWM-User-Id") @Positive int userId
    ) {

    }

}
