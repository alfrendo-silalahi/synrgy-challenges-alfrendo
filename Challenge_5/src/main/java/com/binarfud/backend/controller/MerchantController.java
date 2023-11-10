package com.binarfud.backend.controller;

import com.binarfud.backend.dto.request.CreateMerchantRequest;
import com.binarfud.backend.dto.request.UpdateMerchantAvailabilityRequest;
import com.binarfud.backend.dto.request.UpdateMerchantRequest;
import com.binarfud.backend.dto.response.*;
import com.binarfud.backend.service.MerchantService;
import com.binarfud.backend.util.ResponseStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "api/v1/merchants")
@Tag(
        name = "Merchant Resource",
        description = ""
)
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    private final Map<String, Object> dataObj = new HashMap<>();

    private final Logger log = LoggerFactory.getLogger(MerchantController.class);

    @PostMapping(path = "create-merchant")
    public ResponseEntity<BaseSuccessResponse<Map<String, Object>>> createMerchant(
            @RequestBody CreateMerchantRequest createMerchantRequest
    ) {
        log.info(createMerchantRequest.toString());

        CreateMerchantResponse data = merchantService.createMerchant(createMerchantRequest);

        dataObj.clear();
        dataObj.put("merchants", data);

        BaseSuccessResponse<Map<String, Object>> response = new BaseSuccessResponse<>();
        response.setCode(HttpStatus.CREATED.value());
        response.setStatus(ResponseStatus.SUCCESS.value());
        response.setData(dataObj);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "{merchant-id}/update-open")
    public ResponseEntity<BaseSuccessResponse<Map<String, Object>>> updateMerchantAvailability(
            @PathVariable(name = "merchant-id") UUID merchantId
    ) {
        UpdateMerchantAvailabilityResponse data = merchantService.updateMerchantAvailability(merchantId);

        dataObj.clear();
        dataObj.put("merchant", data);

        BaseSuccessResponse<Map<String, Object>> response = new BaseSuccessResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setStatus(ResponseStatus.SUCCESS.value());
        response.setData(dataObj);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "get-all-merchants")
    public ResponseEntity<BaseSuccessResponse<Map<String, Object>>> getMerchantList(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sortBy", required = false, defaultValue = "name") String name,
            @RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir
    ) {
        MerchantsResponse data = merchantService.getAllMerchants(page, size, name, sortDir);

        dataObj.clear();
        dataObj.put("merchants", data);

        BaseSuccessResponse<Map<String, Object>> response = new BaseSuccessResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setStatus(ResponseStatus.SUCCESS.value());
        response.setData(dataObj);

        return ResponseEntity.ok(response);
    }

    @PutMapping("{merchant-id}/update-merchant")
    public ResponseEntity<BaseSuccessResponse<Map<String, Object>>> updateMerchant(
            @PathVariable("merchant-id") UUID merchantId,
            @RequestBody UpdateMerchantRequest updateMerchantRequest
    ) {
        UpdateMerchantResponse data = merchantService.updateMerchant(merchantId, updateMerchantRequest);

        dataObj.clear();
        dataObj.put("merchant", data);

        BaseSuccessResponse<Map<String, Object>> response = new BaseSuccessResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setStatus(ResponseStatus.SUCCESS.value());
        response.setData(dataObj);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{merchant-id}/delete-merchant")
    public ResponseEntity<BaseSuccessResponse<Map<String, Object>>> deleteMerchant(
            @PathVariable("merchant-id") UUID merchantId
    ) {
        merchantService.deleteMerchant(merchantId);

        BaseSuccessResponse<Map<String, Object>> response = new BaseSuccessResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setStatus(ResponseStatus.SUCCESS.value());
        response.setData(null);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{merchant-id}/get-merchant")
    public ResponseEntity<BaseSuccessResponse<Map<String, Object>>> getMerchant(
            @PathVariable("merchant-id") UUID merchantId
    ) {
        MerchantResponse data = merchantService.getMerchantById(merchantId);

        dataObj.clear();
        dataObj.put("merchant", data);

        BaseSuccessResponse<Map<String, Object>> response = new BaseSuccessResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setStatus(ResponseStatus.SUCCESS.value());
        response.setData(dataObj);

        return ResponseEntity.ok(response);
    }

}
