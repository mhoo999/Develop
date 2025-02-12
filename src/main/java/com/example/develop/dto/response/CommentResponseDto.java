package com.example.develop.dto.response;

import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final String contents;

    public CommentResponseDto(String contents) {
        this.contents = contents;
    }
}
