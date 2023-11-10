package com.binarfud.backend.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateProductBaseRequest {

    @NotEmpty(message = "Product name cannot be empty!")
    private String name;

    private long price;

}
