package com.example.develop.dto.request;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    private final String title;

    private final String Contents;

    private final String username;

    public CreateScheduleRequestDto(String title, String contents, String username) {
        this.title = title;
        this.Contents = contents;
        this.username = username;
    }
}
