package com.alfrendo.challenge4.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateMerchantRequest {

    private String merchantName;

    private String merchantLocation;

    private Boolean open;

}
