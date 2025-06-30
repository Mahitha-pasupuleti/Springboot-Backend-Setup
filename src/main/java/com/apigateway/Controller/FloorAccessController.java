package com.apigateway.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/floors")
public class FloorAccessController {

    @GetMapping("/generalFloor")
    public ResponseEntity<String> entryToGeneralFloor() {
        return ResponseEntity.status(HttpStatus.CREATED).body("Welcome to the general floor! All users are allowed here!");
    }

    @GetMapping("/vipFloor")
    @PreAuthorize("hasRole('VIP_USER')")
    public ResponseEntity<String> entryToVIPFloor() {
        return ResponseEntity.status(HttpStatus.CREATED).body("Welcome to the VIP floor! Only VIP users are allowed!");
    }

}
