package com.binarfud.backend.service;

import com.binarfud.backend.dto.request.CreateOrderRequest;
import com.binarfud.backend.dto.response.CreateOrderResponse;
import com.binarfud.backend.dto.response.OrderListResponse;

import java.util.UUID;

public interface OrderService {

    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);

    OrderListResponse getOrderList(UUID id, int pageNum, int pageSize, String sortBy, String sortDir);

}
