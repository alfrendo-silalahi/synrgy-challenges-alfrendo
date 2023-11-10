package com.binarfud.backend.dto.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductsResponse {

    private int page;

    private int elementOfPage;

    private boolean last;

    private int size;

    private int totalPages;

    private long totalElement;

    private List<ProductElementOfProductsResponse> productList;

}
