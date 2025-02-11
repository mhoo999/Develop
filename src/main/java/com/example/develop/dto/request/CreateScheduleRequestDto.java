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

    @NotBlank
    private final String username;

    public CreateScheduleRequestDto(String title, String contents, String username) {
        this.title = title;
        this.Contents = contents;
        this.username = username;
    }
}
