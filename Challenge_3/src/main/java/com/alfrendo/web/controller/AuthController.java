package com.alfrendo.web.controller;

import com.alfrendo.web.dto.RegisterBuyerDto;
import com.alfrendo.web.dto.RegisterMerchantDto;
import com.alfrendo.web.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping(path = "/register/merchant")
    public String showRegisterMerchantForm(Model model) {
        var registerMerchantDto = new RegisterMerchantDto();
        model.addAttribute("registerMerchant", registerMerchantDto);
        return "register-merchant";
    }

    @PostMapping(path = "/register/merchant/create")
    public String registerMerchant(@ModelAttribute RegisterMerchantDto registerMerchantDto) {
        authService.createUserMerchant(registerMerchantDto);
        return "redirect:/register/merchant";
    }

    @GetMapping(path = "/register/buyer")
    public String showRegisterBuyerForm(Model model) {
        var registerBuyerDto = new RegisterBuyerDto();
        model.addAttribute("registerBuyer", registerBuyerDto);
        return "register-buyer";
    }

    @PostMapping(path = "/register/buyer/create")
    public String registerBuyer(@ModelAttribute RegisterBuyerDto registerBuyerDto) {
        authService.createUserBuyer(registerBuyerDto);
        return "redirect:/register/buyer";
    }

}
