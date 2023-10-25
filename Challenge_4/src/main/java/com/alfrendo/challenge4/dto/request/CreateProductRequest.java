package com.alfrendo.challenge4.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateProductRequest {

    private String productName;

    private Long price;

}
