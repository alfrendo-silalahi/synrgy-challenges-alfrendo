package com.binarfud.backend.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateMerchantAvailabilityRequest {

    private boolean open;

}
