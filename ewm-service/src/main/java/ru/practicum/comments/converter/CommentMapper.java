package ru.practicum.comments.converter;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.comments.dto.CommentDto;
import ru.practicum.comments.model.Comment;
import ru.practicum.util.Util;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "created",expression = "java(getConvertDate(comment))")
    @Mapping(target = "author",expression = "java(getIdAuthor(comment))")
    @Mapping(target = "event",expression = "java(getIdEvent(comment))")
    CommentDto toCommentDto(Comment comment);

    default String getConvertDate(Comment comment) {
        return comment.getCreated().format(Util.getFormatter());
    }

    default int getIdAuthor(Comment comment) {
        return comment.getAuthor().getId();
    }

    default int getIdEvent(Comment comment) {
        return comment.getEvent().getId();
    }
}
