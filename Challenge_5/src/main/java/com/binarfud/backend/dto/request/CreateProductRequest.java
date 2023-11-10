package com.binarfud.backend.dto.request;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateProductRequest {

    private UUID merchantId;

    private String name;

    private Long price;

}
