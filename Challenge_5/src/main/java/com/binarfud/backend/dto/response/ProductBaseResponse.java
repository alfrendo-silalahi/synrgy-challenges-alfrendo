package com.binarfud.backend.dto.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductBaseResponse {

    private UUID id;

    private String name;

    private Long price;

    private boolean isDeleted;

}
