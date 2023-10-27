package com.alfrendo.challenge4.service;

import com.alfrendo.challenge4.dto.request.CreateOrderRequest;
import com.alfrendo.challenge4.dto.response.CreateOrderResponse;
import com.alfrendo.challenge4.dto.response.OrderListResponse;

import java.util.UUID;

public interface OrderService {

    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);

    OrderListResponse getOrderList(UUID id, int pageNum, int pageSize, String sortBy, String sortDir);

}
