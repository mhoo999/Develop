package com.example.develop.controller;

import com.example.develop.dto.request.CreateScheduleRequestDto;
import com.example.develop.dto.request.DeleteScheduleRequestDto;
import com.example.develop.dto.request.UpdateScheduleRequestDto;
import com.example.develop.dto.response.ScheduleResponseDto;
import com.example.develop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 스케줄을 저장하는 메소드입니다.
    // 제목과 컨텐츠, 유저 이름을 전달받습니다.(유저이름은 로그인 기능이 없어 임시로 사용합니다.)
    // 아이디와 제목, 컨텐츠를 반환합니다.
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@Validated @RequestBody CreateScheduleRequestDto requestDto) {

        ScheduleResponseDto scheduleRequestDto = scheduleService.save(requestDto.getTitle(), requestDto.getContents(), requestDto.getUserId());

        return new ResponseEntity<>(scheduleRequestDto, HttpStatus.CREATED);
    }

    // 모든 스케줄을 조회하는 메소드입니다.
    // 모든 스케줄의 아이디, 제목, 컨텐츠를 반환합니다.
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();

        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    // 아이디로 컨텐츠를 조회하는 메소드입니다.
    // 아이디, 제목, 컨텐츠를 반환합니다.
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    // 아이디로 컨텐츠를 조회하고, 저장된 유저가 맞다면 삭제하는 메소드입니다.
    // 유저 이름을 전달받습니다.(유저 이름은 로그인 기능이 없어 임시로 사용합니다.)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Validated @PathVariable Long id, @RequestBody DeleteScheduleRequestDto requestDto) {
        scheduleService.delete(id, requestDto.getUserId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@Validated @PathVariable Long id, @RequestBody UpdateScheduleRequestDto requestDto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.update(id, requestDto.getTitle(), requestDto.getContents(), requestDto.getUserId());

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

}
