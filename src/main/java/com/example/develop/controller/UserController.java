package com.example.develop.controller;

import com.example.develop.dto.request.DeleteUserRequestDto;
import com.example.develop.dto.request.SignUpRequestDto;
import com.example.develop.dto.request.UpdatePasswordRequestDto;
import com.example.develop.dto.response.SignUpResponseDto;
import com.example.develop.dto.response.UserResponseDto;
import com.example.develop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 유저를 저장하는 메소드입니다.
    // 유저 네임, 이메일, 비밀번호를 전달받습니다.
    // 아이디와 이름, 이메일을 반환합니다.
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {

        SignUpResponseDto signUpResponseDto = userService.signUp(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    // 아이디로 유저를 조회하는 메소드입니다.
    // 이름과 이메일을 반환합니다.
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {

        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 아이디로 유저를 조회하고, 비밀번호를 변경하는 메소드입니다.
    // 기존 비밀번호와 신규 비밀번호를 전달받습니다.
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UpdatePasswordRequestDto requestDto) {

        userService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 저장된 모든 유저 정보를 조회합니다.
    // 저장된 모든 이름과 이메일을 반환합니다.
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> userResponseDtoList = userService.findAll();

        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    // 아이디로 유저를 조회하고, 비밀번호 합불 여부에 따라 해당 아이디의 정보를 삭제합니다.
    // 비밀번호를 전달받습니다.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody DeleteUserRequestDto requestDto) {
        userService.delete(id, requestDto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
