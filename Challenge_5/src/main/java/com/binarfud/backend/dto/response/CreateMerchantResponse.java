package com.binarfud.backend.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateMerchantResponse {

    private UUID id;

    private String name;

    private String location;

    private boolean open;

    private LocalDateTime createdAt;

}
