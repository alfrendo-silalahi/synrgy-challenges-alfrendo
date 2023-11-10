package com.binarfud.backend.dto.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MerchantsResponse {

    private int page;

    private int elementInPage;

    private int size;

    private int totalPages;

    private long totalElements;

    private boolean last;

    private List<MerchantElementOfAllMerchants> merchantList;

}
