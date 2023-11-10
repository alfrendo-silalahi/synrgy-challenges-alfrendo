package com.binarfud.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(
        name = "Tutorial Resource",
        description = "Tutorial Resource Description"
)
@RequestMapping(path = "api/tutorials")
public class TutorialController {

    @Operation(
            summary = "Create Tutorial",
            description = "Create Tutorial Description"
    )
    @PostMapping
    public ResponseEntity<String> createTutorial() {
        return new ResponseEntity<>("Create Tutorial", HttpStatus.CREATED);
    }

}
