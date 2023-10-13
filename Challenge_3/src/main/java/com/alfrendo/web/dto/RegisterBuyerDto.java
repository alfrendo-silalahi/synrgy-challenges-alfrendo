package com.alfrendo.web.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterBuyerDto {

    private Long id;

    private String email;

    private String username;

    private String password;

}
