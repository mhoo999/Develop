package com.example.develop.controller;

import com.example.develop.dto.request.DeleteUserRequestDto;
import com.example.develop.dto.request.LoginRequestDto;
import com.example.develop.dto.request.SignUpRequestDto;
import com.example.develop.dto.request.UpdatePasswordRequestDto;
import com.example.develop.dto.response.LoginResponseDto;
import com.example.develop.dto.response.SignUpResponseDto;
import com.example.develop.dto.response.UserResponseDto;
import com.example.develop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto, HttpServletRequest request) {

        LoginResponseDto responseDto = userService.login(requestDto.getEmail(), requestDto.getPassword());

        if (responseDto.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 서버 측 세션 생성
        HttpSession session = request.getSession();
        session.setAttribute("userId", responseDto.getId());
        session.setMaxInactiveInterval(30 * 60);

        // 세션 ID를 쿠키에 저장
        ResponseCookie cookie = ResponseCookie.from("JSESSIONID", session.getId() )
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(30)
                .sameSite("Strict")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok().headers(headers).body(responseDto);
    }

}
