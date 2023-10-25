package com.alfrendo.challenge4.controller;

import com.alfrendo.challenge4.dto.response.ProductListResponse;
import com.alfrendo.challenge4.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "api/v1/products")
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

    @GetMapping
    public ResponseEntity<ProductListResponse> getProductList(
            @RequestParam(name = "sortBy", required = false, defaultValue = "productName") String sortBy,
            @RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir
    ) {
        var response = productService.getProductList(sortBy, sortDir);
        return ResponseEntity.ok(response);
    }

}
