package com.alfrendo.challenge4.controller;

import com.alfrendo.challenge4.dto.request.CreateMerchantRequest;
import com.alfrendo.challenge4.dto.request.UpdateMerchantAvailabilityRequest;
import com.alfrendo.challenge4.dto.response.CreateMerchantResponse;
import com.alfrendo.challenge4.dto.response.MerchantListResponse;
import com.alfrendo.challenge4.dto.response.ProductListResponse;
import com.alfrendo.challenge4.dto.response.UpdateMerchantAvailabilityResponse;
import com.alfrendo.challenge4.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "api/v1/merchants")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping
    public ResponseEntity<CreateMerchantResponse> createMerchant(
            @RequestBody CreateMerchantRequest createMerchantRequest
    ) {
        var response = merchantService.createMerchant(createMerchantRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "{merchant-id}/update-open")
    public ResponseEntity<UpdateMerchantAvailabilityResponse> updateMerchantAvailability(
            @PathVariable(name = "merchant-id") UUID merchantId,
            @RequestBody UpdateMerchantAvailabilityRequest updateMerchantAvailabilityRequest
    ) {
        var response = merchantService.updateMerchantAvailability(merchantId, updateMerchantAvailabilityRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<MerchantListResponse> getMerchantList(
            @RequestParam(name = "open", required = false) Boolean open
    ) {
        var response = merchantService.getMerchantList(open);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "{merchant-id}/products")
    public ResponseEntity<ProductListResponse> getProductListByMerchant(
            @PathVariable(name = "merchant-id") UUID merchantId
    ) {
        var response = merchantService.getProductListByMerchant(merchantId);
        return ResponseEntity.ok(response);
    }

}
