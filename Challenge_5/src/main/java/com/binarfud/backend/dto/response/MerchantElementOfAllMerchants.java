package com.binarfud.backend.dto.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MerchantElementOfAllMerchants {

    private UUID id;

    private String name;

    private String location;

    private Boolean open;

}
