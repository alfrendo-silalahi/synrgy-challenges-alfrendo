package com.alfrendo.challenge4.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/orders")
@Tag(
        name = "Order Resource",
        description = "Order Resource will be handled by the OrderController class"
)
@RequiredArgsConstructor
public class OrderController {

}
