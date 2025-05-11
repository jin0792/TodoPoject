package com.example.todoproject.schedules.service;

import com.example.todoproject.schedules.dto.responsedto.ScheduleResponseDto;
import com.example.todoproject.schedules.entity.Schedule;
import com.example.todoproject.schedules.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    @Transactional
    public ScheduleResponseDto addSchedule(String writerId, String title, String content) {

        Schedule schedule = new Schedule(writerId, title, content);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getWriterId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent()
        );
    }

    // 일정 전체 조회
    @Transactional
    public Page<ScheduleResponseDto> findAll(Pageable pageable) {
        Page<Schedule> schedules = scheduleRepository.findAll(pageable);

        return schedules.map(schedule -> new ScheduleResponseDto(schedule.getId(), schedule.getWriterId(), schedule.getTitle(), schedule.getContent()));

    }

    // 일정 상세 조회
    @Transactional
    public ScheduleResponseDto findById(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 조회 할 수 없습니다." + scheduleId)
        );

        return new ScheduleResponseDto(schedule.getId(), schedule.getWriterId(), schedule.getTitle(), schedule.getContent());
    }

    // 일정 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long scheduleId, String title, String content) {

        Schedule schedule = scheduleRepository.findByOrElseThrow(scheduleId);

        schedule.updateSchedule(title, content);

        return new ScheduleResponseDto(schedule.getId(), schedule.getWriterId(), schedule.getTitle(), schedule.getContent());
    }

    // 일정 삭제
    @Transactional
    public void deleteSchedule(Long scheduleId) {

        Schedule schedule = scheduleRepository.findByOrElseThrow(scheduleId);

        scheduleRepository.delete(schedule);
    }
}
