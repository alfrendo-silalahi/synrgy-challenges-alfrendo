package com.alfrendo.web.service;

import com.alfrendo.web.dto.BuyerProductDto;
import com.alfrendo.web.dto.CartDto;
import com.alfrendo.web.dto.CartGroupDto;
import com.alfrendo.web.dto.OrderDetailDto;
import com.alfrendo.web.mapper.ProductMapper;
import com.alfrendo.web.model.Order;
import com.alfrendo.web.model.OrderDetail;
import com.alfrendo.web.repository.OrderDetailRepository;
import com.alfrendo.web.repository.OrderRepository;
import com.alfrendo.web.repository.ProductRepository;
import com.alfrendo.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimpleOrderService implements OrderService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    private final OrderDetailRepository orderDetailRepository;

    @Override
    public BuyerProductDto findProductById(Long productId) {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        return ProductMapper.mapProductToBuyerProductDto(product);
    }

    @Override
    public void createOrder(Long userId, Long productId, OrderDetailDto orderDetailDto) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        var product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));


        for (var order : user.getOrders()) {

            for (var orderDetail : order.getOrderDetails()) {

                // order sudah ada + produk sama (asumsi dari toko sama)
                if (orderDetail.getProduct().getId().equals(product.getId())) {

                    var foundOrderDetail = orderDetail;

                    foundOrderDetail.setQuantity(orderDetailDto.getQuantity());
                    foundOrderDetail.setTotalPrice((int) ((int) orderDetailDto.getQuantity() * foundOrderDetail.getProduct().getPrice()));

                    orderDetailRepository.save(foundOrderDetail);

                    return;

                }

            }

        }

        for (var order : user.getOrders()) {

            for (var orderDetail : order.getOrderDetails()) {

                // order sudah ada + produk beda (asumsi dari toko yang sama)
                if (orderDetail.getProduct().getMerchant().getId().equals(product.getMerchant().getId())) {

                    var foundOrder = order;

                    var newOrderDetail = OrderDetail.builder()
                            .quantity(orderDetailDto.getQuantity())
                            .totalPrice((int) ((int) orderDetailDto.getQuantity() * product.getPrice()))
                            .product(product)
                            .order(foundOrder)
                            .build();

                    orderDetailRepository.save(newOrderDetail);

                    return;
                }

            }

        }

        // order belum ada + produk belum ada (toko beda)
        var order = Order.builder()
                .completed(false)
                .user(user)
                .build();

        var newOrder = orderRepository.save(order);

        var orderDetail = OrderDetail.builder()
                .quantity(orderDetailDto.getQuantity())
                .totalPrice((int) ((int) orderDetailDto.getQuantity() * product.getPrice()))
                .order(newOrder)
                .product(product)
                .build();

        orderDetailRepository.save(orderDetail);

    }

    @Override
    public List<CartGroupDto> getCart(Long userId) {

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        // ambil semua order
        var orders = user.getOrders();

        List<CartGroupDto> cartGroupDtoList = new ArrayList<>();

        for (var order : orders) {

            var cartGroupDto = CartGroupDto.builder()
                    .cartDtos(new ArrayList<>())
                    .build();

            cartGroupDto.setStore(order.getOrderDetails().get(0).getProduct().getMerchant().getMerchantName());

            var totalPrice = 0;

            for (var orderDetail : order.getOrderDetails()) {

                cartGroupDto.getCartDtos().add(
                        CartDto.builder()
                                .productName(orderDetail.getProduct().getProductName())
                                .price(orderDetail.getProduct().getPrice())
                                .quantity(orderDetail.getQuantity())
                                .totalPricePerProduct(Long.valueOf(orderDetail.getTotalPrice()))
                                .build()
                );

                totalPrice += orderDetail.getTotalPrice();

            }

            cartGroupDto.setTotalPrice((long) totalPrice);

            cartGroupDtoList.add(cartGroupDto);
        }

        return cartGroupDtoList;

    }

}
