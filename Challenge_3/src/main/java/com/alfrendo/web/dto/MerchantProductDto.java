package com.alfrendo.web.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MerchantProductDto {

    private Long id;

    private String productName;

    private Long price;

    private Long merchantId;

}
