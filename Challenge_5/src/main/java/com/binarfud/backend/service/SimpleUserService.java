package com.binarfud.backend.service;

import com.binarfud.backend.dto.request.CreateUserRequest;
import com.binarfud.backend.dto.request.UpdateUserRequest;
import com.binarfud.backend.dto.response.CreateUserResponse;
import com.binarfud.backend.dto.response.DeleteUserResponse;
import com.binarfud.backend.dto.response.UpdateUserResponse;
import com.binarfud.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SimpleUserService implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        // var user = modelMapper.map(createUserRequest, User.class);
        // var newUser = userRepository.save(user);

        var newUserId = UUID.fromString(userRepository.createUser(
                createUserRequest.getUsername(),
                createUserRequest.getEmailAddress(),
                createUserRequest.getPassword()
        ));

        var createUserResponse = modelMapper.map(createUserRequest, CreateUserResponse.class);
        createUserResponse.setId(newUserId);

        return createUserResponse;
    }

    @Override
    public UpdateUserResponse updateUser(UUID userId, UpdateUserRequest updateUserRequest) {
        var id = UUID.fromString(userRepository.updateUser(
                userId,
                updateUserRequest.getUsername(),
                updateUserRequest.getEmailAddress(),
                updateUserRequest.getPassword()
        ));

        var updateUserResponse = modelMapper.map(updateUserRequest, UpdateUserResponse.class);
        updateUserResponse.setId(id);

        return updateUserResponse;
    }

    @Override
    public DeleteUserResponse deleteUser(UUID userId) {
        userRepository.deleteUser(userId);
        return new DeleteUserResponse("User deleted successfully");
    }

}
