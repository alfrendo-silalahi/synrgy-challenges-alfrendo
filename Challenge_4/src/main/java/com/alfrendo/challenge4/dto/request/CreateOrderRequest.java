package com.alfrendo.challenge4.dto.request;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateOrderRequest {

    private UUID userId;

    private String destinationAddress;

}
