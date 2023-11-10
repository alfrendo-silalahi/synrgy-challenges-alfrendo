package com.binarfud.backend.service;

import com.binarfud.backend.dto.request.CreateProductRequest;
import com.binarfud.backend.dto.request.UpdateProductBaseRequest;
import com.binarfud.backend.dto.response.*;
import com.binarfud.backend.exception.ResourceNotFoundException;
import com.binarfud.backend.model.Merchant;
import com.binarfud.backend.model.Product;
import com.binarfud.backend.repository.MerchantRepository;
import com.binarfud.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimpleProductService implements ProductService {

    private final MerchantRepository merchantRepository;

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Override
    public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {
        Merchant merchant = merchantRepository.findById(createProductRequest.getMerchantId())
                .orElseThrow(() -> new ResourceNotFoundException("Merchant with id " + createProductRequest.getMerchantId() + " not found!"));

        // TODO: check if merchant was deleted
        Product product = Product.builder()
                .name(createProductRequest.getName())
                .price(createProductRequest.getPrice())
                .merchant(merchant)
                .build();

        Product newProduct = productRepository.save(product);

        return CreateProductResponse.builder()
                .id(newProduct.getId())
                .name(newProduct.getName())
                .price(newProduct.getPrice())
                .merchantId(merchant.getId())
                .createdAt(merchant.getCreatedAt())
                .build();
    }

    @Override
    public ProductsResponse getProductList(int page, int size, String sortBy, String sortDir) {
        var sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        // TODO: only not deleted product
        Page<Product> productPage= productRepository.findAll(pageable);

        return ProductsResponse.builder()
                .page(productPage.getNumber() + 1)
                .elementOfPage(productPage.getNumberOfElements())
                .last(productPage.isLast())
                .size(productPage.getSize())
                .totalPages(productPage.getTotalPages())
                .totalElement(productPage.getTotalElements())
                .productList(
                        productPage.getContent().stream().map(
                                product -> ProductElementOfProductsResponse.builder()
                                        .id(product.getId())
                                        .merchantId(product.getMerchant().getId())
                                        .name(product.getName())
                                        .price(product.getPrice())
                                        .createdAt(product.getCreatedAt())
                                        .updatedAt(product.getUpdatedAt())
                                        .build()
                        ).collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public ProductsByMerchantResponse getProductListByMerchant(UUID merchantId, int page, int size, String sortBy, String sortDir) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Merchant with id " + merchantId + " not found!"
                ));

        // TODO: check if merchant was deleted or not

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<Product> productPage = productRepository.findAllByMerchant(merchant, pageable);

        return ProductsByMerchantResponse.builder()
                .merchantId(merchantId)
                .page(productPage.getNumber() + 1)
                .elementOfPage(productPage.getNumberOfElements())
                .last(productPage.isLast())
                .size(productPage.getSize())
                .totalPages(productPage.getTotalPages())
                .totalElement(productPage.getTotalElements())
                .productList(
                        productPage.getContent().stream().map(
                                product -> ProductElementOfProductsResponse.builder()
                                        .id(product.getId())
                                        .merchantId(product.getMerchant().getId())
                                        .name(product.getName())
                                        .price(product.getPrice())
                                        .createdAt(product.getCreatedAt())
                                        .updatedAt(product.getUpdatedAt())
                                        .build()
                        ).collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public UpdateProductResponse updateDetailProduct(UUID productId, UpdateProductBaseRequest updateProductRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + productId + " not found!"));
        
        product.setName(updateProductRequest.getName());
        product.setPrice(updateProductRequest.getPrice());

        Product updatedProduct = productRepository.save(product);

        return UpdateProductResponse.builder()
                .id(updatedProduct.getId())
                .name(updatedProduct.getName())
                .price(updatedProduct.getPrice())
                .createdAt(updatedProduct.getCreatedAt())
                .updatedAt(updatedProduct.getUpdatedAt())
                .build();
    }

    @Override
    public void deleteProduct(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + productId + " not foound!"));

        product.setDeleted(true);
        productRepository.save(product);
    }

}
