package com.binarfud.backend.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UpdateMerchantAvailabilityResponse {

    private UUID id;

    private String name;

    private String location;

    private boolean open;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
