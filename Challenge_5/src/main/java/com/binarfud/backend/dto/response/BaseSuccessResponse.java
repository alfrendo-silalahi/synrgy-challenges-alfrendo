package com.binarfud.backend.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BaseSuccessResponse<T> {

    private int code;

    private String status;

    private T data;

}
