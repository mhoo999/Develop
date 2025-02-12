package com.example.develop.service;

import com.example.develop.dto.response.CommentResponseDto;
import com.example.develop.entity.Comment;
import com.example.develop.entity.Schedule;
import com.example.develop.entity.User;
import com.example.develop.repository.CommentRepository;
import com.example.develop.repository.ScheduleRepository;
import com.example.develop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
