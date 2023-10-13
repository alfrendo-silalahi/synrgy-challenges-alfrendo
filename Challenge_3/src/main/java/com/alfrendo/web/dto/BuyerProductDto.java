package com.alfrendo.web.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BuyerProductDto {

    private Long id;

    private String productName;

    private Long price;

    private String store;

    private String location;

    private String owner;

}
