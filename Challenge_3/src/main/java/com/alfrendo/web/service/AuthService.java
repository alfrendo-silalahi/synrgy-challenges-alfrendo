package com.alfrendo.web.service;

import com.alfrendo.web.dto.RegisterBuyerDto;
import com.alfrendo.web.dto.RegisterMerchantDto;

public interface AuthService {

    void createUserMerchant(RegisterMerchantDto registerMerchantDto);

    void createUserBuyer(RegisterBuyerDto registerBuyerDto);

}
