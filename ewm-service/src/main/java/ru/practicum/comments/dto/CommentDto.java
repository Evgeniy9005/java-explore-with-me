package ru.practicum.comments.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder(toBuilder = true)
public class CommentDto {

    private final int id;

    private final String text;

    private final int author;

    private final int event;

    private final String created;

}
