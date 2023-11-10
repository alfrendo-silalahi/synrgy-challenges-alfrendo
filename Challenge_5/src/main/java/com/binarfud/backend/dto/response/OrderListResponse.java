package com.binarfud.backend.dto.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderListResponse {

    private int pageNum;

    private int pageTotal;

    private int orderTotalInPage;

    private int pageSize;

    private boolean isLastPage;

    private List<OrderBaseResponse> orderBaseResponses;

}
