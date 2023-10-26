package com.alfrendo.challenge4.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserBaseRequest {

    private String username;

    private String emailAddress;

    private String password;

}
