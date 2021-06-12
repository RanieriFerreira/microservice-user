package com.studies.microserviceuser.service;

import com.studies.microserviceuser.domain.User;
import com.studies.microserviceuser.exception.BadRequestException;
import com.studies.microserviceuser.repository.UserRepository;
import com.studies.microserviceuser.request.UserPostRequest;
import com.studies.microserviceuser.request.UserPutRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserRepository userRepository;

    public List<User> listAll() {
        log.info("m=listUsers stage=init");
        return userRepository.findAll();
    }

    public List<User> findUsersByName(String userName) {
        return userRepository.findUserByName(userName);
    }

    public User findUserByIdOrThrowBadRequestException(Long userId) {
        log.info("m=getUserById stage=init");
        return userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }

    public User save(UserPostRequest userPostRequest) {
        log.info("m=saveUser stage=init");
        var user = UserMapper.INSTANCE.toUser(userPostRequest);
//        var user = User.builder().name(userPostRequest.getName()).build();
        return userRepository.save(user);
    }

    public void delete(Long userId) {
        log.info("m=deleteUser stage=init");
        userRepository.delete(findUserByIdOrThrowBadRequestException(userId));
    }

    public void update(UserPutRequest userPutRequest) {
        log.info("m=replaceUser stage=init");
        var savedUser = findUserByIdOrThrowBadRequestException(userPutRequest.getUserId());
        var user = UserMapper.INSTANCE.toUser(userPutRequest);
        user.setUserId(savedUser.getUserId());
//        var user = User.builder()
//                .userId(savedUser.getUserId())
//                .name(userPutRequest.getName())
//                .build();
        userRepository.save(user);
    }
}
