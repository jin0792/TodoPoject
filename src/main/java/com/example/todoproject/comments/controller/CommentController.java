package com.example.todoproject.comments.controller;

import com.example.todoproject.comments.dto.requestdto.CommentRequestDto;
import com.example.todoproject.comments.dto.responsedto.CommentResponseDto;
import com.example.todoproject.comments.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules/{schedulesId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/add")
    public ResponseEntity<CommentResponseDto> addComment(@PathVariable Long schedulesId, @Valid @RequestBody CommentRequestDto dto) {
        CommentResponseDto commentResponseDto =
                commentService.addComment(schedulesId, dto.getWriterId(), dto.getContent(), dto.getParentCommentId());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);

    }

    // 특정 스케줄의 댓글 전체 조회
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAllBySchedule(@PathVariable Long schedulesId) {
        List<CommentResponseDto> commentResponseDto =
                commentService.findAllBySchedule(schedulesId);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    // 특정 게시글의 특정 댓글 조회
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> findById(@PathVariable Long commentId) {
        CommentResponseDto commentResponseDto =
                commentService.findById(commentId);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    // 특정 게시글의 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> update(@PathVariable Long commentId, @Valid @RequestBody CommentRequestDto dto) {
        CommentResponseDto commentResponseDto =
                commentService.update(commentId, dto.getWriterId(), dto.getContent());
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }


    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
