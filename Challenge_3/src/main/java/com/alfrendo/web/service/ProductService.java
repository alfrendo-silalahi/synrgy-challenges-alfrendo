package com.alfrendo.web.service;

import com.alfrendo.web.dto.BuyerProductDto;
import com.alfrendo.web.dto.MerchantProductDto;

import java.util.List;

public interface ProductService {

    List<MerchantProductDto> findAllProductsByMerchantId(Long merchantId);

    void createProduct(Long merchantId, MerchantProductDto merchantProductDto);

    List<BuyerProductDto> findAllProductsForBuyer();

}
