package com.alfrendo.web.mapper;

import com.alfrendo.web.dto.BuyerProductDto;
import com.alfrendo.web.dto.MerchantProductDto;
import com.alfrendo.web.model.Product;

public class ProductMapper {

    public static MerchantProductDto mapProductToMerchantProductDto(Product product) {
        return MerchantProductDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .build();
    }

    public static Product mapMerchantProductDtoToProduct(MerchantProductDto merchantProductDto) {
        return Product.builder()
                .id(merchantProductDto.getId())
                .productName(merchantProductDto.getProductName())
                .price(merchantProductDto.getPrice())
                .build();
    }

    public static BuyerProductDto mapProductToBuyerProductDto(Product product) {
        return BuyerProductDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .store(product.getMerchant().getMerchantName())
                .location(product.getMerchant().getMerchantLocation())
                .owner(product.getMerchant().getUser().getUsername())
                .build();
    }

}
