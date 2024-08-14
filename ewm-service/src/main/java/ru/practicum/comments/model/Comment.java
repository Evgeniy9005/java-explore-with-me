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
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_author")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_event")
    private Event event;

    @Column(name = "created")
    private LocalDateTime created;

}
