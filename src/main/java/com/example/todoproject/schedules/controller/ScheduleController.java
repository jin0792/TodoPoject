package com.example.todoproject.schedules.controller;

import com.example.todoproject.schedules.dto.requestdto.ScheduleRequestDto;
import com.example.todoproject.schedules.dto.responsedto.ScheduleResponseDto;
import com.example.todoproject.schedules.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> addSchedule(@Valid @RequestBody ScheduleRequestDto dto) {
        ScheduleResponseDto scheduleResponseDto =
                scheduleService.addSchedule(
                        dto.getWriterId(),
                        dto.getTitle(),
                        dto.getContent()
                );

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);

    }

    // 일정 전체 조회
    @GetMapping
    public ResponseEntity<Page<ScheduleResponseDto>> findAll(
            @PageableDefault(direction = Sort.Direction.DESC, sort = "createdAt") Pageable pageable
    ) {
        Page<ScheduleResponseDto> responseDtoList = scheduleService.findAll(pageable);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    // 일정 상세 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long scheduleId) {

        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(scheduleId);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    // 일정 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long scheduleId,
            @Valid @RequestBody ScheduleRequestDto dto
    ) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(
                scheduleId, dto.getTitle(), dto.getContent()
        );
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> deleteSchedule(@PathVariable Long scheduleId) {

        scheduleService.deleteSchedule(scheduleId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
