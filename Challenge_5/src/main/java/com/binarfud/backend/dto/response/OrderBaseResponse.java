package com.binarfud.backend.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderBaseResponse {

    private UUID userId;

    private String username;

    private UUID orderId;

    private String destinationAddress;

    private boolean completed;

    private LocalDateTime orderTime;

}
