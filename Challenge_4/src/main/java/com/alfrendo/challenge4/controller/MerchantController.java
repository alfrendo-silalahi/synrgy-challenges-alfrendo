package com.alfrendo.challenge4.controller;

import com.alfrendo.challenge4.dto.request.CreateMerchantRequest;
import com.alfrendo.challenge4.dto.request.UpdateMerchantAvailabilityRequest;
import com.alfrendo.challenge4.dto.request.UpdateProductBaseRequest;
import com.alfrendo.challenge4.dto.response.*;
import com.alfrendo.challenge4.service.MerchantService;
import com.alfrendo.challenge4.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "api/v1/merchants")
@Tag(
        name = "Merchant Resource",
        description = "Merchant Resource will be handled by the MerchantController class"
)
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    private final ProductService productService;

    private final Logger log = LoggerFactory.getLogger(MerchantController.class);

    @PostMapping
    public ResponseEntity<CreateMerchantResponse> createMerchant(
            @RequestBody CreateMerchantRequest createMerchantRequest
    ) {
        var response = merchantService.createMerchant(createMerchantRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "{merchant-id}/open")
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

}
