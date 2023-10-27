package com.alfrendo.challenge4.controller;

import com.alfrendo.challenge4.dto.request.CreateOrderRequest;
import com.alfrendo.challenge4.dto.response.CreateOrderResponse;
import com.alfrendo.challenge4.dto.response.OrderListResponse;
import com.alfrendo.challenge4.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/orders")
@Tag(
        name = "Order Resource",
        description = "Order Resource will be handled by the OrderController class"
)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(
            @RequestBody CreateOrderRequest createOrderRequest
    ) {
        log.info("Create order request -> {}", createOrderRequest.toString());

        var response = orderService.createOrder(createOrderRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<OrderListResponse> getOrderList(
            @RequestParam(name = "userId") UUID id,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(name = "sortBy", required = false, defaultValue = "orderTime") String sortBy,
            @RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir
    ) {
        log.info("User id -> {}", id);
        log.info("Page number -> {}", pageNum);
        log.info("Page size -> {}", pageSize);
        log.info("Sort by -> {}", sortBy);
        log.info("Sort dir -> {}", sortDir);

        var response = orderService.getOrderList(id, pageNum, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(response);
    }

}
