package com.binarfud.backend.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorBaseResponse <T> {

    private T message;

}
