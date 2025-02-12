package com.example.develop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateCommentRequestDto {

    @NotBlank
    private final String contents;

    @NotNull
    private final Long scheduleId;

    public CreateCommentRequestDto(String contents, Long scheduleId) {
        this.contents = contents;
        this.scheduleId = scheduleId;
    }
}
