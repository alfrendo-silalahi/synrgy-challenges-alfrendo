package com.binarfud.backend.service;

import com.binarfud.backend.dto.request.CreateUserRequest;
import com.binarfud.backend.dto.request.UpdateUserRequest;
import com.binarfud.backend.dto.response.CreateUserResponse;
import com.binarfud.backend.dto.response.DeleteUserResponse;
import com.binarfud.backend.dto.response.UpdateUserResponse;

import java.util.UUID;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequest createUserRequest);

    UpdateUserResponse updateUser(UUID userId, UpdateUserRequest updateUserRequest);

    DeleteUserResponse deleteUser(UUID userId);

}
