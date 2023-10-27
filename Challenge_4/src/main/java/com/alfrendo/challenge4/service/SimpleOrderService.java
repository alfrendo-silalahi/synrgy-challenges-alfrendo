package com.alfrendo.challenge4.service;

import com.alfrendo.challenge4.dto.request.CreateOrderRequest;
import com.alfrendo.challenge4.dto.response.CreateOrderResponse;
import com.alfrendo.challenge4.dto.response.OrderBaseResponse;
import com.alfrendo.challenge4.dto.response.OrderListResponse;
import com.alfrendo.challenge4.exception.ResourceNotFoundException;
import com.alfrendo.challenge4.model.Order;
import com.alfrendo.challenge4.repository.OrderRepository;
import com.alfrendo.challenge4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SimpleOrderService implements OrderService {

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        var user = userRepository.findById(createOrderRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id " + createOrderRequest.getUserId() + " not found!"
                ));

        var order = new Order();
        order.setUser(user);
        order.setDestinationAddress(createOrderRequest.getDestinationAddress());
        var newOrder = orderRepository.save(order);

        var createOrderResponse = new CreateOrderResponse();
        createOrderResponse.setCompleted(newOrder.isCompleted());
        createOrderResponse.setOrderId(newOrder.getId());
        createOrderResponse.setUsername(newOrder.getUser().getUsername());
        createOrderResponse.setDestinationAddress(newOrder.getDestinationAddress());
        createOrderResponse.setUserId(newOrder.getUser().getId());
        createOrderResponse.setOrderTime(newOrder.getOrderTime());

        return createOrderResponse;
    }

    @Override
    public OrderListResponse getOrderList(UUID id, int pageNum, int pageSize, String sortBy, String sortDir) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id " + id + " not found!"
                ));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<Order> orderPage = orderRepository.findAllByUser(user, pageable);
        List<Order> orderList = orderPage.getContent();
        List<OrderBaseResponse> orderBaseResponseList = orderList.stream()
                .map(order -> {
                    var orderBaseResponse = new OrderBaseResponse();
                    orderBaseResponse.setCompleted(order.isCompleted());
                    orderBaseResponse.setOrderId(order.getId());
                    orderBaseResponse.setUsername(order.getUser().getUsername());
                    orderBaseResponse.setDestinationAddress(order.getDestinationAddress());
                    orderBaseResponse.setUserId(order.getUser().getId());
                    orderBaseResponse.setOrderTime(order.getOrderTime());

                    return orderBaseResponse;
                })
                .toList();

        var orderListResponse = new OrderListResponse();
        orderListResponse.setOrderBaseResponses(orderBaseResponseList);
        orderListResponse.setPageNum(orderPage.getNumber() + 1);
        orderListResponse.setPageSize(orderPage.getSize());
        orderListResponse.setOrderTotalInPage(orderPage.getNumberOfElements());
        orderListResponse.setLastPage(orderPage.isLast());
        orderListResponse.setPageTotal(orderPage.getTotalPages());

        return orderListResponse;
    }

}
