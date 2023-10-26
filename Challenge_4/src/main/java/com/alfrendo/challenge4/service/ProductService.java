package com.alfrendo.challenge4.service;

import com.alfrendo.challenge4.dto.request.CreateProductBaseRequest;
import com.alfrendo.challenge4.dto.request.UpdateProductBaseRequest;
import com.alfrendo.challenge4.dto.response.CreateProductResponse;
import com.alfrendo.challenge4.dto.response.DeleteProductResponse;
import com.alfrendo.challenge4.dto.response.ProductBaseResponse;
import com.alfrendo.challenge4.dto.response.ProductListResponse;

import java.util.UUID;

public interface ProductService {

    CreateProductResponse createProduct(UUID merchantId, CreateProductBaseRequest createProductRequest);

    // CreateProductResponse createProduct(UUID merchantId, CreateProductRecordRequest createProductRecordRequest);

    ProductListResponse getProductList(String sortBy, String sortDir);

    ProductListResponse getProductListByMerchant(UUID merchantId);

    ProductBaseResponse updateDetailProduct(UUID merchantId, UUID productId, UpdateProductBaseRequest updateProductRequest);

    DeleteProductResponse deleteProduct(UUID merchantId, UUID productId);

}
