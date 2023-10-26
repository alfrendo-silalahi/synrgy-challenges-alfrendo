package com.alfrendo.challenge4.controller;

import com.alfrendo.challenge4.dto.request.UpdateProductBaseRequest;
import com.alfrendo.challenge4.dto.response.DeleteProductResponse;
import com.alfrendo.challenge4.dto.response.ProductBaseResponse;
import com.alfrendo.challenge4.dto.response.ProductListResponse;
import com.alfrendo.challenge4.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "api/v1")
@Tag(
        name = "Product Resource",
        description = "Product Resource will be handled by the ProductController class"
)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final Logger log = LoggerFactory.getLogger(ProductController.class);

//    @PostMapping(path = "{merchant-id}/products")
//    public ResponseEntity<CreateProductResponse> createProduct(
//            @PathVariable(name = "merchant-id") UUID merchantId,
//            @RequestBody CreateProductRequest createProductRequest
//    ) {
//        log.info("Merchant Id -> {}", merchantId);
//        log.info("Create Product Request -> {}", createProductRequest);
//
//        var response = productService.createProduct(merchantId, createProductRequest);
//        // var response = productService.createProduct(merchantId, createProductRecordRequest);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }

    @GetMapping(path = "/products")
    public ResponseEntity<ProductListResponse> getProductList(
            @RequestParam(name = "sortBy", required = false, defaultValue = "productName") String sortBy,
            @RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir
    ) {
        var response = productService.getProductList(sortBy, sortDir);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "merchants/{merchant-id}/products")
    public ResponseEntity<ProductListResponse> getProductListByMerchant(
            @PathVariable(name = "merchant-id") UUID merchantId
    ) {
        var response = productService.getProductListByMerchant(merchantId);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "merchants/{merchant-id}/products/{product-id}")
    public ResponseEntity<ProductBaseResponse> updateDetailProduct(
            @PathVariable(name = "merchant-id") UUID merchantId,
            @PathVariable(name = "product-id") UUID productId,
            @RequestBody UpdateProductBaseRequest updateProductRequest
    ) {
        log.info(updateProductRequest.toString());
        var response = productService.updateDetailProduct(merchantId, productId, updateProductRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "merchants/{merchant-id}/products/{product-id}")
    public ResponseEntity<DeleteProductResponse> deleteProduct(
            @PathVariable(name = "merchant-id") UUID merchantId,
            @PathVariable(name = "product-id") UUID productId
    ) {
        var response = productService.deleteProduct(merchantId, productId);
        return ResponseEntity.ok(response);
    }

}
