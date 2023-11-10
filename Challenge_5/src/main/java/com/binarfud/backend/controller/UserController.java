package com.binarfud.backend.controller;

import com.binarfud.backend.dto.request.CreateUserRequest;
import com.binarfud.backend.dto.request.UpdateUserRequest;
import com.binarfud.backend.dto.response.CreateUserResponse;
import com.binarfud.backend.dto.response.DeleteUserResponse;
import com.binarfud.backend.dto.response.UpdateUserResponse;
import com.binarfud.backend.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/users")
@Tag(
        name = "User Resource",
        description = "User Resource will be handled by the UserController class"
)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(
            @RequestBody CreateUserRequest createUserRequest
    ) {
        log.info(createUserRequest.toString());
        var response = userService.createUser(createUserRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "{user-id}")
    public ResponseEntity<UpdateUserResponse> updateUser(
            @PathVariable(name = "user-id") UUID userId,
            @RequestBody UpdateUserRequest updateUserRequest
    ) {
        log.info("User id -> {}", userId);
        log.info("Update user request -> {}", updateUserRequest.toString());

        var response = userService.updateUser(userId, updateUserRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "{user-id}")
    public ResponseEntity<DeleteUserResponse> deleteUser(
            @PathVariable(name = "user-id") UUID userId
    ) {
        log.info("User id -> {}", userId);
        var response = userService.deleteUser(userId);
        return ResponseEntity.ok(response);
    }

}
