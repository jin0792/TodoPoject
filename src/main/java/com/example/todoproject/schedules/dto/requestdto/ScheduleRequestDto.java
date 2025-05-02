package com.example.todoproject.schedules.dto.requestdto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    @NotNull
    private final String writerId;

    private final String title;

    private final String content;

    public ScheduleRequestDto(String writerId, String title, String content) {
        this.writerId = writerId;
        this.title = title;
        this.content = content;
    }
}
