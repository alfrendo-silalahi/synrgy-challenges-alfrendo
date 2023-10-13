package com.alfrendo.web.service;

import com.alfrendo.web.dto.RegisterBuyerDto;
import com.alfrendo.web.dto.RegisterMerchantDto;
import com.alfrendo.web.model.Merchant;
import com.alfrendo.web.model.Role;
import com.alfrendo.web.model.User;
import com.alfrendo.web.repository.MerchantRepository;
import com.alfrendo.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleAuthService implements AuthService {

    private final UserRepository userRepository;

    private final MerchantRepository merchantRepository;

    @Override
    public void createUserMerchant(RegisterMerchantDto registerMerchantDto) {
        User user = User.builder()
                .email(registerMerchantDto.getEmail())
                .username(registerMerchantDto.getUsername())
                .password(registerMerchantDto.getPassword())
                .role(Role.MERCHANT)
                .build();

        var newUser = userRepository.save(user);

        var merchant = Merchant.builder()
                .merchantName(registerMerchantDto.getMerchantName())
                .merchantLocation(registerMerchantDto.getMerchantLocation())
                .open(true)
                .user(newUser)
                .build();

        merchantRepository.save(merchant);
    }

    @Override
    public void createUserBuyer(RegisterBuyerDto registerBuyerDto) {
        User user = User.builder()
                .email(registerBuyerDto.getEmail())
                .username(registerBuyerDto.getUsername())
                .password(registerBuyerDto.getPassword())
                .role(Role.BUYER)
                .build();

        userRepository.save(user);
    }

}
