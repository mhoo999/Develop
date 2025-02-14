package com.example.develop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    @NotBlank
    private final String title;

    @NotNull
    private final String Contents;

    @NotNull
    private final Long userId;

    public CreateScheduleRequestDto(String title, String contents, Long userId) {
        this.title = title;
        this.Contents = contents;
        this.userId = userId;
    }
}
