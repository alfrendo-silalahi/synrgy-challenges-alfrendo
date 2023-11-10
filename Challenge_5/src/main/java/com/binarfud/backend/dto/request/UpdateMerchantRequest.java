package com.binarfud.backend.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateMerchantRequest {

    @NotEmpty(message = "name cannot be empty")
    @NotNull(message = "name cannot be null")
    private String name;

    @NotEmpty(message = "location cannot be empty")
    @NotEmpty(message = "location cannot be null")
    private String location;

    private boolean open;

}
