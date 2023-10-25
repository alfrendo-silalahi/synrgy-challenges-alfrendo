package com.alfrendo.challenge4.dto.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MerchantListResponse {

    private List<MerchantBaseResponse> merchantList;

}
