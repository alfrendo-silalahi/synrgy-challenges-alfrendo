package com.binarfud.backend.dto.response;

import com.binarfud.backend.model.Merchant;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductElementOfProductsResponse {

    private UUID id;

    private UUID merchantId;

    private String name;

    private long price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
