package com.example.todoproject.schedules.dto.responsedto;

import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private Long id;

    private String writerId;

    private String title;

    private String contents;

    public ScheduleResponseDto(Long id, String writerId, String title, String contents) {
        this.id = id;
        this.writerId = writerId;
        this.title = title;
        this.contents = contents;
    }
}
