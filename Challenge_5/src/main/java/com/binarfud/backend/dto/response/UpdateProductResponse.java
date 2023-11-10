package com.binarfud.backend.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateProductResponse {

    private UUID id;

    private String name;

    private long price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
