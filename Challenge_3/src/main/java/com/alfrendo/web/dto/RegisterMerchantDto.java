package com.alfrendo.web.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterMerchantDto {

    private Long id;

    private String email;

    private String username;

    private String merchantName;

    private String merchantLocation;

    private String password;

}
