package ru.practicum.comments.model;


import jakarta.persistence.*;
import lombok.*;
import ru.practicum.events.model.Event;
import ru.practicum.users.model.User;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@ToString
@Builder(toBuilder = true)
@Table(name = "comments", schema = "public")
@RequiredArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;

    @Column(name = "text")
    private final String text;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_author")
    private final User author;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_event")
    private final Event event;

    @Column(name = "created")
    private final LocalDateTime created;

}
