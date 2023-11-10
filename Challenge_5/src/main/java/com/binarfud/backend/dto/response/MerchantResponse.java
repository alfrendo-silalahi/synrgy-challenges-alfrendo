package com.binarfud.backend.dto.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MerchantResponse {

    private UUID id;

    private String name;

    private String location;

    private boolean open;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean deleted;

}
