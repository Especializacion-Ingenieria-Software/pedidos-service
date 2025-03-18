package co.edu.itm.restaurant.orders.application.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {


    @GetMapping("/health")
    public String health() {
        return "service's ok";
    }

}
