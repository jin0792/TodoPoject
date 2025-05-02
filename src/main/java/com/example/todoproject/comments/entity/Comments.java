package com.example.todoproject.comments.entity;

import com.example.todoproject.schedules.entity.BaseEntity;
import com.example.todoproject.schedules.entity.Schedules;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor
public class Comments extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String writerId;

    @Column(length = 100, nullable = false)
    private String contents;

    @Column(length = 100, nullable = false)
    private String content_reply;

    @Column(nullable = false)
    private int comment_count;

    @ManyToOne
    @JoinColumn(name = "schedules_id", nullable = false)
    private Schedules schedules;

}
