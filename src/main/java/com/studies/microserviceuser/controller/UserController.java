package com.studies.microserviceuser.controller;

import com.studies.microserviceuser.domain.User;
import com.studies.microserviceuser.request.UserPostRequest;
import com.studies.microserviceuser.request.UserPutRequest;
import com.studies.microserviceuser.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@Log4j2
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> listAllUsers() {
        log.info("method = GET, path = '/users'");
        return new ResponseEntity<>(userService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable Long userId) {
        log.info("method = GET, path = '/users/{}'", userId);
        return new ResponseEntity<>(userService.findUserByIdOrThrowBadRequestException(userId), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<User>> findUserById(@RequestParam("userName") String username) {
        log.info("method = GET, path = '/find?userName={}'", username);
        return new ResponseEntity<>(userService.findUsersByName(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody UserPostRequest userPostRequest) {
        log.info("method = POST, path = 'users'");
        return new ResponseEntity<>(userService.save(userPostRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        log.info("method = DELETE, path = '/users/{}'", userId);
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replaceUser(@RequestBody UserPutRequest userPutRequest) {
        log.info("method = PUT, path = '/users'");
        userService.update(userPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
