package com.alfrendo.challenge4.service;

import com.alfrendo.challenge4.dto.request.CreateMerchantRequest;
import com.alfrendo.challenge4.dto.request.UpdateMerchantAvailabilityRequest;
import com.alfrendo.challenge4.dto.response.CreateMerchantResponse;
import com.alfrendo.challenge4.dto.response.MerchantListResponse;
import com.alfrendo.challenge4.dto.response.ProductListResponse;
import com.alfrendo.challenge4.dto.response.UpdateMerchantAvailabilityResponse;

import java.util.UUID;

public interface MerchantService {

    CreateMerchantResponse createMerchant(CreateMerchantRequest createMerchantRequest);

    UpdateMerchantAvailabilityResponse updateMerchantAvailability(
            UUID merchantId, UpdateMerchantAvailabilityRequest merchantAvailabilityRequest);

    MerchantListResponse getMerchantList(Boolean open);

    ProductListResponse getProductListByMerchant(UUID merchantId);
}
