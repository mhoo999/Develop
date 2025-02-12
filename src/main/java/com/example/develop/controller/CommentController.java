package com.example.develop.controller;

import com.example.develop.dto.request.CreateCommentRequestDto;
import com.example.develop.dto.response.CommentResponseDto;
import com.example.develop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> save(@Validated @RequestBody CreateCommentRequestDto requestDto) {
        CommentResponseDto commentRequestDto = commentService.save(requestDto.getContents(), requestDto.getScheduleId());

        return new ResponseEntity<>(commentRequestDto, HttpStatus.CREATED);
    }

}
