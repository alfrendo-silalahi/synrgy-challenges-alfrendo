package com.alfrendo.challenge4;

import com.alfrendo.challenge4.dto.response.MerchantBaseResponse;
import com.alfrendo.challenge4.dto.response.MerchantListResponse;
import com.alfrendo.challenge4.model.Merchant;
import com.alfrendo.challenge4.repository.MerchantRepository;
import com.alfrendo.challenge4.service.MerchantService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = Challenge4Application.class)
public class MerchantServiceTest {

    @MockBean
    private MerchantRepository merchantRepository;

    @Autowired
    private MerchantService merchantService;

    @Test
    @DisplayName("Test get merchant list where open is null")
    public void testGetMerchantListWhereOpenIsNull() {
        when(merchantRepository.findAll())
                .thenReturn(List.of(
                        new Merchant(
                                UUID.fromString("9cb5e94c-f715-4f8f-a124-857240422d6c"),
                                "Alfrendo Store",
                                "Samosir",
                                true,
                                null)
                ));

        assertNotNull(merchantService.getMerchantList(null), "Merchant list should not null");

        var expected = new MerchantListResponse();
        expected.setMerchantList(
                List.of(
                        new MerchantBaseResponse(
                                UUID.fromString("9cb5e94c-f715-4f8f-a124-857240422d6c"),
                                "Alfrendo Store",
                                "Samosir",
                                true
                        )
                )
        );

        // assertEquals(expected, merchantService.getMerchantList(null), "Merchant list response should be equals");
        // verify(merchantRepository, times(1)).findAllByOpen(null);
    }

}
