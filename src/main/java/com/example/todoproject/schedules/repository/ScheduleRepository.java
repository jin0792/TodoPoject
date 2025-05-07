package com.example.todoproject.schedules.repository;

import com.example.todoproject.schedules.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    default Schedule findByOrElseThrow(Long scheduleId) {
        return findById(scheduleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID를 찾을 수 없습니다." + scheduleId));
    }

    Page<Schedule> findAll(Pageable pageable);
}
