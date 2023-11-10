package com.binarfud.backend.dto.response;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductsByMerchantResponse {

    private UUID merchantId;

    private int page;

    private int elementOfPage;

    private boolean last;

    private int size;

    private int totalPages;

    private long totalElement;

    private List<ProductElementOfProductsResponse> productList;

}
