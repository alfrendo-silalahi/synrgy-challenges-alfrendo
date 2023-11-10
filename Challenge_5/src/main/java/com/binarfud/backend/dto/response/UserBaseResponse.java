package com.binarfud.backend.dto.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserBaseResponse {

    private UUID id;

    private String username;

    private String emailAddress;

    private String password;

}
