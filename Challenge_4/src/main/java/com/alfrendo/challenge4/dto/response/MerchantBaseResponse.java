package com.alfrendo.challenge4.dto.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MerchantBaseResponse {

    private UUID id;

    private String merchantName;

    private String merchantLocation;

    private Boolean open;

}
