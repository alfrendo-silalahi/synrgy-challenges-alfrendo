package com.alfrendo.web.controller;

import com.alfrendo.web.dto.OrderDetailDto;
import com.alfrendo.web.model.OrderDetail;
import com.alfrendo.web.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(path = "/buyer/products/{product-id}/orders/create")
    public String showCreateOrderForm(
            @PathVariable(name = "product-id") Long productId,
            Model model
    ) {
        var product = orderService.findProductById(productId);
        model.addAttribute("product", product);

        var orderDetail = new OrderDetailDto();
        model.addAttribute("orderDetail", orderDetail);

        return "buyer-create-order";
    }

    @PostMapping(path = "/buyer/products/{product-id}/orders/create/process")
    public String createOrder(
            @PathVariable(name = "product-id") Long productId,
            @ModelAttribute OrderDetailDto orderDetailDto
    ) {
        var userId = 2L;
        orderService.createOrder(userId, productId, orderDetailDto);

        return "redirect:/buyer/products";
    }

    @GetMapping(path = "/buyer/cart")
    public String showCart(Model model) {
        var userId = 2L;
        var carts = orderService.getCart(userId);

        model.addAttribute("carts", carts);

        return "buyer-cart";
    }

}
