package com.example.develop.service;

import com.example.develop.config.PasswordEncoder;
import com.example.develop.dto.response.LoginResponseDto;
import com.example.develop.dto.response.SignUpResponseDto;
import com.example.develop.dto.response.UserResponseDto;
import com.example.develop.entity.User;
import com.example.develop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpResponseDto signUp(String username, String email, String password) {

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(username, email, encodedPassword);
        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    public UserResponseDto findById(Long id) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        return new UserResponseDto(findUser.getUsername(), findUser.getEmail());
    }

    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        checkPassword(findUser, oldPassword);

        String encodedPassword = passwordEncoder.encode(newPassword);
        findUser.updatePassword(encodedPassword);
    }

    public List<UserResponseDto> findAll() {

        return userRepository.findAll().stream().map(UserResponseDto::toDto).toList();
    }

    public void delete(Long id, String password) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        checkPassword(findUser, password);

        userRepository.delete(findUser);
    }

    // 비밀번호 심사를 진행하는 메소드입니다.
    private void checkPassword(User user, String password) {

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
        }
    }

    public LoginResponseDto login(String email, String password) {

        User findUser = userRepository.findByEmailOrElseThrow(email);

        checkPassword(findUser, password);

        return new LoginResponseDto(findUser.getId(), findUser.getUsername());
    }
}
