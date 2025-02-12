package com.example.develop.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateCommentRequestDto {

    private final String contents;

    @NotNull
    private final Long userId;

    public UpdateCommentRequestDto(String contents, Long userId) {
        this.contents = contents;
        this.userId = userId;
    }
}
