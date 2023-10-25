package com.alfrendo.challenge4.service;

import com.alfrendo.challenge4.dto.request.CreateMerchantRequest;
import com.alfrendo.challenge4.dto.request.UpdateMerchantAvailabilityRequest;
import com.alfrendo.challenge4.dto.response.*;
import com.alfrendo.challenge4.exception.ResourceNotFoundException;
import com.alfrendo.challenge4.model.Merchant;
import com.alfrendo.challenge4.repository.MerchantRepository;
import com.alfrendo.challenge4.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SimpleMerchantService implements MerchantService {

    private final MerchantRepository merchantRepository;

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Override
    public CreateMerchantResponse createMerchant(CreateMerchantRequest createMerchantRequest) {
        var merchant = modelMapper.map(createMerchantRequest, Merchant.class);
        var newMerchant = merchantRepository.save(merchant);
        return modelMapper.map(newMerchant, CreateMerchantResponse.class);
    }

    @Override
    public UpdateMerchantAvailabilityResponse updateMerchantAvailability(
            UUID merchantId,
            UpdateMerchantAvailabilityRequest merchantAvailabilityRequest
    ) {
        var merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Merchant", "Id", merchantId.toString()));
        merchant.setOpen(merchantAvailabilityRequest.getOpen());
        var updatedMerchant = merchantRepository.save(merchant);
        return modelMapper.map(updatedMerchant, UpdateMerchantAvailabilityResponse.class);
    }

    @Override
    public MerchantListResponse getMerchantList(Boolean open) {
        List<Merchant> merchants;

        if (open == null) {
            merchants = merchantRepository.findAll();
        } else {
            merchants = merchantRepository.findAllByOpen(open);
        }

        var merchantBaseResponseList = merchants.stream()
                .map(merchant -> modelMapper.map(merchant, MerchantBaseResponse.class))
                .toList();

        return new MerchantListResponse(merchantBaseResponseList);
    }

    @Override
    public ProductListResponse getProductListByMerchant(UUID merchantId) {
        var merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Merchant", "id", merchantId.toString()));

        var products = productRepository.findAllByMerchant(merchant);

        var productBaseResponseList = products.stream()
                .map(product -> modelMapper.map(product, ProductBaseResponse.class))
                .toList();

        return new ProductListResponse(productBaseResponseList);
    }

}
