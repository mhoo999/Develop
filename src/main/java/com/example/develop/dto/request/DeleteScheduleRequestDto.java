package com.example.develop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteScheduleRequestDto {

    @NotBlank
    private final String username;

    public DeleteScheduleRequestDto(String username) {
        this.username = username;
    }
}
