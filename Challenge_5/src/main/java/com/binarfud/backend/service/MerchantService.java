package com.binarfud.backend.service;

import com.binarfud.backend.dto.request.CreateMerchantRequest;
import com.binarfud.backend.dto.request.UpdateMerchantRequest;
import com.binarfud.backend.dto.response.*;

import java.util.UUID;

public interface MerchantService {

    CreateMerchantResponse createMerchant(CreateMerchantRequest createMerchantRequest);

    UpdateMerchantAvailabilityResponse updateMerchantAvailability(UUID merchantId);

    UpdateMerchantResponse updateMerchant(UUID merchantId, UpdateMerchantRequest updateMerchantRequest);

    void deleteMerchant(UUID merchantId);

    MerchantResponse getMerchantById(UUID merchantId);

    MerchantsResponse getAllMerchants(int page, int size, String sortBy, String sortDir);

}
