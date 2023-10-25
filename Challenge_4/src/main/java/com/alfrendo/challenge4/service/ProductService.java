package com.alfrendo.challenge4.service;

import com.alfrendo.challenge4.dto.request.CreateProductRequest;
import com.alfrendo.challenge4.dto.response.CreateProductResponse;
import com.alfrendo.challenge4.dto.response.ProductListResponse;

import java.util.UUID;

public interface ProductService {

    CreateProductResponse createProduct(UUID merchantId, CreateProductRequest createProductRequest);

    // CreateProductResponse createProduct(UUID merchantId, CreateProductRecordRequest createProductRecordRequest);

    ProductListResponse getProductList(String sortBy, String sortDir);

}
