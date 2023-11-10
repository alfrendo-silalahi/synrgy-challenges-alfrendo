package com.binarfud.backend.service;

import com.binarfud.backend.dto.request.CreateMerchantRequest;
import com.binarfud.backend.dto.request.UpdateMerchantAvailabilityRequest;
import com.binarfud.backend.dto.request.UpdateMerchantRequest;
import com.binarfud.backend.dto.response.*;
import com.binarfud.backend.exception.ResourceNotFoundException;
import com.binarfud.backend.model.Merchant;
import com.binarfud.backend.repository.MerchantRepository;
import com.binarfud.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimpleMerchantService implements MerchantService {

    private final MerchantRepository merchantRepository;

    @Override
    public CreateMerchantResponse createMerchant(CreateMerchantRequest createMerchantRequest) {
        Merchant merchant = Merchant.builder()
                .name(createMerchantRequest.getName())
                .location(createMerchantRequest.getLocation())
                .open(createMerchantRequest.isOpen())
                .build();
        Merchant newMerchant = merchantRepository.save(merchant);
        return CreateMerchantResponse.builder()
                .id(newMerchant.getId())
                .name(newMerchant.getName())
                .location(newMerchant.getLocation())
                .open(newMerchant.isOpen())
                .createdAt(newMerchant.getCreatedAt())
                .build();
    }

    @Override
    public UpdateMerchantAvailabilityResponse updateMerchantAvailability(UUID merchantId) {
        var merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Merchant with id " + merchantId + " not found!"
                ));
        merchant.setOpen(!merchant.isOpen());
        var updatedMerchant = merchantRepository.save(merchant);

        log.warn("OPEN -> {}", updatedMerchant.isOpen());

        return UpdateMerchantAvailabilityResponse
                .builder()
                .id(updatedMerchant.getId())
                .open(updatedMerchant.isOpen())
                .name(updatedMerchant.getName())
                .location(updatedMerchant.getName())
                .createdAt(updatedMerchant.getCreatedAt())
                .updatedAt(updatedMerchant.getUpdatedAt())
                .build();
    }

    @Override
    public UpdateMerchantResponse updateMerchant(UUID merchantId, UpdateMerchantRequest updateMerchantRequest) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Merchant with " + merchantId + " not found!"));

        merchant.setName(updateMerchantRequest.getName());
        merchant.setLocation(updateMerchantRequest.getLocation());
        merchant.setOpen(updateMerchantRequest.isOpen());

        Merchant updatedMerchant = merchantRepository.save(merchant);

        return UpdateMerchantResponse.builder()
                .id(updatedMerchant.getId())
                .name(updatedMerchant.getName())
                .location(updatedMerchant.getLocation())
                .open(updatedMerchant.isOpen())
                .createdAt(updatedMerchant.getCreatedAt())
                .updatedAt(updatedMerchant.getUpdatedAt())
                .build();
    }

    @Override
    public void deleteMerchant(UUID merchantId) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Merchant with " + merchantId + " not found!"));

        merchant.setDeleted(true);
        merchantRepository.save(merchant);
    }

    @Override
    public MerchantResponse getMerchantById(UUID merchantId) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Merchant with " + merchantId + " not found!"));
        return MerchantResponse
                .builder()
                .id(merchant.getId())
                .name(merchant.getName())
                .location(merchant.getLocation())
                .open(merchant.isOpen())
                .createdAt(merchant.getCreatedAt())
                .updatedAt(merchant.getUpdatedAt())
                .deleted(merchant.isDeleted())
                .build();
    }

    @Override
    public MerchantsResponse getAllMerchants(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<Merchant> merchantsPage = merchantRepository.findAll(pageable);

        return MerchantsResponse.builder()
                .page(merchantsPage.getNumber() + 1)
                .elementInPage(merchantsPage.getNumberOfElements())
                .size(merchantsPage.getSize())
                .totalPages(merchantsPage.getTotalPages())
                .totalElements(merchantsPage.getTotalElements())
                .last(merchantsPage.isLast())
                .merchantList(merchantsPage.getContent().stream()
                        .map(merchant -> MerchantElementOfAllMerchants.builder()
                                .id(merchant.getId())
                                .name(merchant.getName())
                                .location(merchant.getLocation())
                                .open(merchant.isOpen())
                                .build()).toList())
                .build();
    }

}
