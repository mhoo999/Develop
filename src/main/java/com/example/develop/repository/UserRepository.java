package com.example.develop.repository;

import com.example.develop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
    Optional<User> findByEmail(String email);

    default User findUserByUsernameOrElseThrow(String username) {
        return findUserByUsername(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist username : " + username));
    }

    default User findByIdOrElseThrow(long id) {
        return findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id : " + id));
    }

    default User findByEmailOrElseThrow(String email) {
        return findByEmail(email).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist email : " + email));
    }
}
