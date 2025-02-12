package com.example.develop.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {

    private final String title;

    private final String contents;

    @NotNull
    private final Long userId;

    public UpdateScheduleRequestDto(String title, String contents, Long userId) {
        this.title = title;
        this.contents = contents;
        this.userId = userId;
    }
}
