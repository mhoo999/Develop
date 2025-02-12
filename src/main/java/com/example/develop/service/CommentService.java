package com.example.develop.service;

import com.example.develop.dto.response.CommentResponseDto;
import com.example.develop.entity.Comment;
import com.example.develop.entity.Schedule;
import com.example.develop.entity.User;
import com.example.develop.repository.CommentRepository;
import com.example.develop.repository.ScheduleRepository;
import com.example.develop.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    public CommentResponseDto save(String contents, Long scheduleId) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        User findUser = userRepository.findByIdOrElseThrow(findSchedule.getUser().getId());

        Comment comment = new Comment(contents, findUser, findSchedule);

        findSchedule.getComments().add(comment);
        findUser.getComments().add(comment);

        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment.getContents());
    }

    public CommentResponseDto update(Long id, String contents, Long userId) {
        userRepository.findByIdOrElseThrow(userId);
        scheduleRepository.findByIdOrElseThrow(id);

        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        if (contents != null) {
            findComment.updateContents(contents);
        }

        return new CommentResponseDto(findComment.getContents());
    }

    public void delete(Long id, @NotBlank Long userId, @NotBlank Long scheduleId) {
        User findUser = userRepository.findByIdOrElseThrow(userId);
        findUser.getComments().removeIf(comment -> comment.getId().equals(id));

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        findSchedule.getComments().removeIf(comment -> comment.getId().equals(id));

        commentRepository.delete(commentRepository.findByIdOrElseThrow(id));
    }

    public List<CommentResponseDto> getAll(Long userId, Long scheduleId) {
        return commentRepository.findAll().stream()
                .filter(comment -> (comment.getUser().getId().equals(userId)) || (comment.getSchedule().getId().equals(scheduleId)))
                .map(CommentResponseDto::toDto)
                .collect(Collectors.toList());
    }

}
