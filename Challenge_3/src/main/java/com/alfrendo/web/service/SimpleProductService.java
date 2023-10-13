package com.alfrendo.web.service;

import com.alfrendo.web.dto.BuyerProductDto;
import com.alfrendo.web.dto.MerchantProductDto;
import com.alfrendo.web.mapper.ProductMapper;
import com.alfrendo.web.repository.MerchantRepository;
import com.alfrendo.web.repository.ProductRepository;
import com.alfrendo.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleProductService implements ProductService {

    private final UserRepository userRepository;

    private final MerchantRepository merchantRepository;

    private final ProductRepository productRepository;

    @Override
    public List<MerchantProductDto> findAllProductsByMerchantId(Long merchantId) {
        // find merchant
        var merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new RuntimeException("Merchant not found!"));

        // find products
        var products = productRepository.findAllByMerchant(merchant);

        return products.stream().map(ProductMapper::mapProductToMerchantProductDto).toList();
    }

    @Override
    public void createProduct(Long merchantId, MerchantProductDto merchantProductDto) {
        // find merchant
        var merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new RuntimeException("Merchant not found!"));

        var product = ProductMapper.mapMerchantProductDtoToProduct(merchantProductDto);
        product.setMerchant(merchant);

        productRepository.save(product);
    }

    @Override
    public List<BuyerProductDto> findAllProductsForBuyer() {
        var products = productRepository.findAll();
        return products.stream().map(ProductMapper::mapProductToBuyerProductDto).toList();
    }

}
