package com.ru.vsu.woodemai;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mapping")
public class Controller {

    @GetMapping
    public ResponseEntity<String> getResponse() {
        return ResponseEntity.ok("Hello");
    }
}
