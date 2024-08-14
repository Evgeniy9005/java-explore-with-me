package ru.practicum.comments.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import ru.practicum.events.model.Event;
import ru.practicum.users.model.User;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class CommentDto {

    private final int id;

    private final String text;

    private final int author;

    private final int event;

    private final String created;

}
