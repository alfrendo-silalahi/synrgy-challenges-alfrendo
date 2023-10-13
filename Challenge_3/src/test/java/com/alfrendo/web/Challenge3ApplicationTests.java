package com.alfrendo.web;

import com.alfrendo.web.dto.RegisterMerchantDto;
import com.alfrendo.web.model.Role;
import com.alfrendo.web.model.User;
import com.alfrendo.web.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Challenge3ApplicationTests {

    private final UserRepository userRepository;

    Challenge3ApplicationTests(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void registerUserAsMerchant() {
        var registerMerchantDto = RegisterMerchantDto.builder()
                .email("kevinsanjaya@gmail.com")
                .username("kevin123")
                .merchantName("Kevin Sanjaya Store")
                .merchantLocation("Bandung")
                .password("senjaya123")
                .build();

        var user = User.builder()
                .email(registerMerchantDto.getEmail())
                .username(registerMerchantDto.getUsername())
                .password(registerMerchantDto.getPassword())
                .role(Role.BUYER)
                .build();

        var newUser = userRepository.save(user);

        assertThat(newUser).isNotNull();
    }

}
