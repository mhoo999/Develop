package com.example.develop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteUserRequestDto {

    @NotBlank
    private final String password;

    public DeleteUserRequestDto(String password) {
        this.password = password;
    }
}
