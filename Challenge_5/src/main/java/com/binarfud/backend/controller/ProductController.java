package com.binarfud.backend.controller;

import com.binarfud.backend.dto.request.CreateProductRequest;
import com.binarfud.backend.dto.request.UpdateProductBaseRequest;
import com.binarfud.backend.dto.response.*;
import com.binarfud.backend.service.ProductService;
import com.binarfud.backend.util.ResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "api/v1/products")
@Tag(
        name = "Product Resource",
        description = "Product Resource Description"
)
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    private final Map<String, Object> dataObj = new HashMap<>();

    @PostMapping("create-product")
    public ResponseEntity<BaseSuccessResponse<Map<String, Object>>> createProduct(
            @RequestBody CreateProductRequest createProductRequest
    ) {
        CreateProductResponse data = productService.createProduct(createProductRequest);

        dataObj.clear();
        dataObj.put("product", data);

        BaseSuccessResponse<Map<String, Object>> response = new BaseSuccessResponse<>();
        response.setCode(HttpStatus.CREATED.value());
        response.setStatus(ResponseStatus.SUCCESS.value());
        response.setData(dataObj);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all products",
            description = "Get all products description"
    )
    @GetMapping(path = "get-all-products")
    public ResponseEntity<BaseSuccessResponse<Map<String, Object>>> getProductList(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size  ", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sortBy", required = false, defaultValue = "name") String sortBy,
            @RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir
    ) {
        ProductsResponse data = productService.getProductList(page, size, sortBy, sortDir);

        dataObj.clear();
        dataObj.put("products", data);

        BaseSuccessResponse<Map<String, Object>> response = new BaseSuccessResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setStatus(ResponseStatus.SUCCESS.value());
        response.setData(dataObj);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "get-all-products-by-merchant")
    public ResponseEntity<BaseSuccessResponse<Map<String, Object>>> getProductListByMerchant(
            @RequestParam(name = "merchantId") UUID merchantId,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sortBy", required = false, defaultValue = "name") String sortBy,
            @RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir
    ) {
        ProductsByMerchantResponse data = productService.getProductListByMerchant(merchantId, page, size, sortBy, sortDir);
        
        dataObj.clear();
        dataObj.put("products", data);

        BaseSuccessResponse<Map<String, Object>> response = new BaseSuccessResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setStatus(ResponseStatus.SUCCESS.value());
        response.setData(dataObj);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "{product-id}/update-product")
    public ResponseEntity<BaseSuccessResponse<Map<String, Object>>> updateDetailProduct(
            @PathVariable("product-id") UUID productId,
            @Valid @RequestBody UpdateProductBaseRequest updateProductRequest
    ) {
        log.info(updateProductRequest.toString());

        UpdateProductResponse data = productService.updateDetailProduct(productId, updateProductRequest);
        
        dataObj.clear();
        dataObj.put("product", data);

        BaseSuccessResponse<Map<String, Object>> response = new BaseSuccessResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setStatus(ResponseStatus.SUCCESS.value());
        response.setData(dataObj);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "{product-id}/delete-product")
    public ResponseEntity<BaseSuccessResponse<Map<String, Object>>> deleteProduct(
            @PathVariable("product-id") UUID productId
    ) {
        productService.deleteProduct(productId);
        
        BaseSuccessResponse<Map<String, Object>> response = new BaseSuccessResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setStatus(ResponseStatus.SUCCESS.value());
        response.setData(null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
