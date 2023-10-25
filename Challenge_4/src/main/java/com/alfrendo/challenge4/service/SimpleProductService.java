package com.alfrendo.challenge4.service;

import com.alfrendo.challenge4.dto.request.CreateProductRequest;
import com.alfrendo.challenge4.dto.response.CreateProductResponse;
import com.alfrendo.challenge4.dto.response.ProductBaseResponse;
import com.alfrendo.challenge4.dto.response.ProductListResponse;
import com.alfrendo.challenge4.exception.ResourceNotFoundException;
import com.alfrendo.challenge4.model.Product;
import com.alfrendo.challenge4.repository.MerchantRepository;
import com.alfrendo.challenge4.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SimpleProductService implements ProductService {

    private final MerchantRepository merchantRepository;

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

//    @Override
//    public CreateProductResponse createProduct(UUID merchantId, CreateProductRecordRequest createProductRecordRequest) {
//        var merchant = merchantRepository.findById(merchantId)
//                .orElseThrow(() -> new ResourceNotFoundException("Merchant", "Id", merchantId.toString()));
//
//        var product = modelMapper.map(createProductRecordRequest, Product.class);
//        product.setMerchant(merchant);
//
//        var newProduct = productRepository.save(product);
//
//        return modelMapper.map(newProduct, CreateProductResponse.class);
//    }

    @Override
    public CreateProductResponse createProduct(UUID merchantId, CreateProductRequest createProductRequest) {
        var merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Merchant", "Id", merchantId.toString()));

        var product = modelMapper.map(createProductRequest, Product.class);
        product.setMerchant(merchant);

        var newProduct = productRepository.save(product);

        return modelMapper.map(newProduct, CreateProductResponse.class);
    }

    @Override
    public ProductListResponse getProductList(String sortBy, String sortDir) {
        var sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        var productList = productRepository.findAll(sort);
        var productBaseResponseList = productList.stream()
                .map(product -> modelMapper.map(product, ProductBaseResponse.class))
                .toList();
        return new ProductListResponse(productBaseResponseList);
    }

}
