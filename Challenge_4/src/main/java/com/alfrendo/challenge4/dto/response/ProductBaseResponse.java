package com.alfrendo.challenge4.dto.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductBaseResponse {

    private UUID id;

    private String productName;

    private Long price;

}
