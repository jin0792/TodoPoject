package com.example.todoproject.comments.entity;

import com.example.todoproject.schedules.entity.BaseEntity;
import com.example.todoproject.schedules.entity.Schedule;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String writerId;

    @Column(length = 100, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> replies = new ArrayList<>();

    @Builder
    public Comment(Schedule schedule, String writerId, String content, Comment parentComment) {
        this.schedule = schedule;
        this.writerId = writerId;
        this.content = content;
        this.parentComment = parentComment;

        if (this.parentComment != null && this.parentComment.parentComment != null) {
            throw new IllegalArgumentException("대댓글의 대댓글은 달 수 없습니다.");
        }
    }

    public void update(String writerId, String content) {
        this.writerId = writerId;
        this.content = content;
    }
}
