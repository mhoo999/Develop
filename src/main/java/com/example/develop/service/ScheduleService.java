package com.example.develop.service;

import com.example.develop.dto.response.ScheduleResponseDto;
import com.example.develop.entity.Schedule;
import com.example.develop.entity.User;
import com.example.develop.repository.ScheduleRepository;
import com.example.develop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(String title, String contents, Long userId) {

        User findUser = userRepository.findByIdOrElseThrow(userId);

        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findUser);

        findUser.getSchedules().add(schedule);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents());
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::toDto).toList();
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents());
    }

    public void delete(Long id, Long userId) {
        User findUser = userRepository.findByIdOrElseThrow(userId);
        findUser.getSchedules().removeIf(schedule -> schedule.getId().equals(id));

        scheduleRepository.delete(scheduleRepository.findByIdOrElseThrow(id));
    }

    public ScheduleResponseDto update(Long id, String title, String contents, Long userId) {
        userRepository.findByIdOrElseThrow(userId);
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        if (title != null) {
            findSchedule.updateTitle(title);
        }

        if (contents != null) {
            findSchedule.updateContents(contents);
        }

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents());
    }
}
