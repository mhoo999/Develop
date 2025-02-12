package com.example.develop.controller;

import com.example.develop.dto.request.CreateCommentRequestDto;
import com.example.develop.dto.request.DeleteCommentRequestDto;
import com.example.develop.dto.request.UpdateCommentRequestDto;
import com.example.develop.dto.response.CommentResponseDto;
import com.example.develop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 코멘트를 저장하는 메소드입니다.
    @PostMapping
    public ResponseEntity<CommentResponseDto> save(@Validated @RequestBody CreateCommentRequestDto requestDto) {
        CommentResponseDto commentRequestDto = commentService.save(requestDto.getContents(), requestDto.getScheduleId());

        return new ResponseEntity<>(commentRequestDto, HttpStatus.CREATED);
    }

    // 코멘트를 수정하는 메소드입니다.
    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponseDto> update(@Validated @PathVariable Long id, @RequestBody UpdateCommentRequestDto requestDto) {
        CommentResponseDto commentResponseDto = commentService.update(id, requestDto.getContents(), requestDto.getUserId());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    // 코멘트를 삭제하는 메소드입니다.
    @DeleteMapping("/{id}")
    public ResponseEntity<CommentResponseDto> delete(@Validated @PathVariable Long id, @RequestBody DeleteCommentRequestDto requestDto) {
        commentService.delete(id, requestDto.getUserId(), requestDto.getScheduleId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
