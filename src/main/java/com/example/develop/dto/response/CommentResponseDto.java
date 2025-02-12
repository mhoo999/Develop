package com.example.develop.dto.response;

import com.example.develop.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final String contents;

    public CommentResponseDto(String contents) {
        this.contents = contents;
    }

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(comment.getContents());
    }
}
