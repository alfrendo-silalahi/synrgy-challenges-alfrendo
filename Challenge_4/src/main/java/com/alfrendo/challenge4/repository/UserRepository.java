package com.alfrendo.challenge4.repository;

import com.alfrendo.challenge4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Procedure(procedureName = "create_user")
    String createUser(
            @Param("u_username") String username,
            @Param("u_email_address") String emailAddress,
            @Param("u_password") String password
    );

    @Procedure(procedureName = "update_user")
    String updateUser(
            @Param("u_id") UUID id,
            @Param("u_username") String username,
            @Param("u_email_address") String emailAddress,
            @Param("u_password") String password
    );

    @Procedure(procedureName = "delete_user")
    void deleteUser(@Param("u_id") UUID id);

}
