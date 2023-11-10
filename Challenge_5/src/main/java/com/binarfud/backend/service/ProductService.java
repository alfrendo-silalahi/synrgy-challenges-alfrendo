package com.binarfud.backend.service;

import com.binarfud.backend.dto.request.CreateProductRequest;
import com.binarfud.backend.dto.request.UpdateProductBaseRequest;
import com.binarfud.backend.dto.response.*;

import java.util.UUID;

public interface ProductService {

    CreateProductResponse createProduct(CreateProductRequest createProductRequest);

    ProductsResponse getProductList(int page, int size, String sortBy, String sortDir);

    ProductsByMerchantResponse getProductListByMerchant(UUID merchantId, int page, int size, String sortBy, String sortDir);

    UpdateProductResponse updateDetailProduct(UUID productId, UpdateProductBaseRequest updateProductRequest);

    void deleteProduct(UUID productId);

}
