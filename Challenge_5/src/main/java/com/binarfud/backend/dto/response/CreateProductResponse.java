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
public class CreateProductResponse {

    private UUID id;

    private UUID merchantId;

    private String name;

    private Long price;

    private LocalDateTime createdAt;

}
