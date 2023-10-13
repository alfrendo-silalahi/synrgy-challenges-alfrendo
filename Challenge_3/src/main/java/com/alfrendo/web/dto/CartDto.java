package com.alfrendo.web.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CartDto {

    private String productName;

    private Integer quantity;

    private Long price;

    private Long totalPricePerProduct;

}
