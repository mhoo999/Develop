package com.example.develop.dto.request;

import lombok.Getter;

@Getter
public class DeleteScheduleRequestDto {

    private final String username;

    public DeleteScheduleRequestDto(String username) {
        this.username = username;
    }
}
