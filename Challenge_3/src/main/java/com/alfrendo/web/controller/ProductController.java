package com.alfrendo.web.controller;

import com.alfrendo.web.dto.MerchantProductDto;
import com.alfrendo.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "/merchant/{merchant-id}/products")
    public String showMerchantProductList(
            @PathVariable(name = "merchant-id") Long merchantId,
            Model model
    ) {
        var productDtos = productService.findAllProductsByMerchantId(merchantId);
        model.addAttribute("products", productDtos);
        model.addAttribute("merchantId", merchantId);
        return "merchant-products";
    }

    @GetMapping(path = "/merchant/{merchant-id}/products/create")
    public String showCreateProductForm(
            @PathVariable(name = "merchant-id") Long merchantId,
            Model model
    ) {
        var productDto = new MerchantProductDto();
        productDto.setMerchantId(merchantId);
        model.addAttribute("product", productDto);
        return "merchant-create-product";
    }

    @PostMapping(path = "/merchant/{merchant-id}/products/create/process")
    public String createProduct(
            @PathVariable(name = "merchant-id") Long merchantId,
            @ModelAttribute MerchantProductDto merchantProductDto
    ) {
        System.out.println(merchantId);
        productService.createProduct(merchantId, merchantProductDto);
        return "redirect:/merchant/" + merchantId + "/products";
    }

    @GetMapping(path = "/buyer/products")
    public String showBuyerProductList(Model model) {
        var products = productService.findAllProductsForBuyer();
        model.addAttribute("products", products);
        return "buyer-products";
    }

}
