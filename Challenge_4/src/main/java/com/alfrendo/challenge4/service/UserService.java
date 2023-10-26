package com.alfrendo.challenge4.service;

import com.alfrendo.challenge4.dto.request.CreateUserRequest;
import com.alfrendo.challenge4.dto.request.UpdateUserRequest;
import com.alfrendo.challenge4.dto.response.CreateUserResponse;
import com.alfrendo.challenge4.dto.response.DeleteUserResponse;
import com.alfrendo.challenge4.dto.response.UpdateUserResponse;

import java.util.UUID;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequest createUserRequest);

    UpdateUserResponse updateUser(UUID userId, UpdateUserRequest updateUserRequest);

    DeleteUserResponse deleteUser(UUID userId);

}
