package com.example.todoproject.schedules.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor
@AllArgsConstructor
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String writerId;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public Schedule(String writerId, String title, String content) {
        this.writerId = writerId;
        this.title = title;
        this.content = content;
    }

    public void updateSchedule(String title, String content) {
        this.title = title;
        this.content = content;
    }

}