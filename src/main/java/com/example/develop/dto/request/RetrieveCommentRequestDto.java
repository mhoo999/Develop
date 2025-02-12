package com.example.develop.dto.request;

import jakarta.annotation.Nullable;
import lombok.Getter;

@Getter
public class RetrieveCommentRequestDto {

    @Nullable
    private final Long userId;

    @Nullable
    private final Long scheduleId;

    public RetrieveCommentRequestDto(@Nullable Long userId, @Nullable Long scheduleId) {
        this.userId = userId;
        this.scheduleId = scheduleId;
    }
}
