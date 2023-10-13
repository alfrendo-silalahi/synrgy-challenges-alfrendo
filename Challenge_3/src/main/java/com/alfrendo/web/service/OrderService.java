package com.alfrendo.web.service;

import com.alfrendo.web.dto.BuyerProductDto;
import com.alfrendo.web.dto.CartGroupDto;
import com.alfrendo.web.dto.OrderDetailDto;

import java.util.List;

public interface OrderService {

    BuyerProductDto findProductById(Long productId);

    void createOrder(Long userId, Long productId, OrderDetailDto orderDetailDto);

    List<CartGroupDto> getCart(Long userId);

}
