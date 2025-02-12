package com.example.develop.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DeleteScheduleRequestDto {

    @NotNull
    private final Long userId;

    public DeleteScheduleRequestDto(Long userId) {
        this.userId = userId;
    }
}
