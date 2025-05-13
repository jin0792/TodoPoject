package com.example.todoproject.comments.dto.requestdto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotNull
    private final String writerId;

    private final String content;

    private Long parentCommentId;


    public CommentRequestDto(String writerId, String content, Long parentCommentId) {
        this.writerId = writerId;
        this.content = content;
        this.parentCommentId = parentCommentId;
    }
}
