package com.example.todoproject.comments.service;

import com.example.todoproject.comments.dto.responsedto.CommentResponseDto;
import com.example.todoproject.comments.entity.Comment;
import com.example.todoproject.comments.repository.CommentRepository;
import com.example.todoproject.schedules.entity.Schedule;
import com.example.todoproject.schedules.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentResponseDto addComment(Long scheduleId, String writerId, String content, Long parentCommentId) {

        Schedule schedule = scheduleRepository.findByOrElseThrow(scheduleId);

        Comment parentComment = null;
        if (parentCommentId != null) {
            parentComment = commentRepository.findById(parentCommentId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "댓글이 존재하지 않습니다."));

            if (parentComment.getParentComment() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "대댓글에는 대댓글을 달 수 없습니다.");
            }
        }

        Comment comment = new Comment(schedule, writerId, content, parentComment);
        Comment saveComment = commentRepository.save(comment);

        return new CommentResponseDto(
                saveComment.getId(),
                saveComment.getWriterId(),
                saveComment.getContent(),
                saveComment.getSchedule().getId(),
                parentComment != null ? parentComment.getId() : null, 0
                // 저장 직후에는 총 댓글 수를 알 필요 없다.
        );
    }

    @Transactional
    public List<CommentResponseDto> findAllBySchedule(Long scheduleId) {
       List<Comment> comments = commentRepository.findByScheduleId(scheduleId);
       int totalCount = comments.size();

       return comments.stream()
               .map(comment -> new CommentResponseDto(
                       comment.getId(),
                       comment.getWriterId(),
                       comment.getContent(),
                       comment.getSchedule().getId(),
                       comment.getParentComment() != null ? comment.getParentComment().getId() : null,
                       totalCount
               ))
               .toList();
    }

    public CommentResponseDto findById(Long commentId) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);

        return new CommentResponseDto(
                comment.getId(),
                comment.getWriterId(),
                comment.getContent(),
                comment.getSchedule().getId(),
                comment.getParentComment() != null ? comment.getParentComment().getId() : null, 0
                // 단일 댓글 조회는 총 댓글 수 필요 없게 함.
        );
    }

    @Transactional
    public CommentResponseDto update(Long CommentId, String writerId, String content) {
        Comment comment = commentRepository.findByIdOrElseThrow(CommentId);
        comment.update(writerId, content);

        return new CommentResponseDto(
                comment.getId(),
                comment.getWriterId(),
                comment.getContent(),
                comment.getSchedule().getId(),
                comment.getParentComment() != null ? comment.getParentComment().getId() : null, 0
                // 수정 후 총 댓글 수 필요 없다.
        );
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);

        commentRepository.delete(comment);
    }

}