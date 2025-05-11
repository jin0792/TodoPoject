package com.example.todoproject.comments.dto.responsedto;

import lombok.Getter;

@Getter
public class CommentResponseDto {

    private Long id;

    private String writerId;

    private String content;

    private Long scheduleId;

    private Long parentCommentId;

    private int commentCount;

    public CommentResponseDto(Long id, String writerId, String content, Long scheduleId, Long parentCommentId, int commentCount) {
        this.id = id;
        this.writerId = writerId;
        this.content = content;
        this.scheduleId = scheduleId;
        this.parentCommentId = parentCommentId;
        this.commentCount = commentCount;
    }

}