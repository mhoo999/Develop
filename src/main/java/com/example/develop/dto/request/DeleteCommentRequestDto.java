package com.example.develop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteCommentRequestDto {

    @NotBlank
    private final Long userId;

    @NotBlank
    private final Long scheduleId;

    public DeleteCommentRequestDto(Long userId, Long scheduleId) {
        this.userId = userId;
        this.scheduleId = scheduleId;
    }
}
