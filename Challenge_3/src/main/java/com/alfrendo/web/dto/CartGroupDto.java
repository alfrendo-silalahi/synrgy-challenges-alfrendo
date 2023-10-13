package com.alfrendo.web.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CartGroupDto {

    private String store;

    private List<CartDto> cartDtos;

    private Long totalPrice;

}
