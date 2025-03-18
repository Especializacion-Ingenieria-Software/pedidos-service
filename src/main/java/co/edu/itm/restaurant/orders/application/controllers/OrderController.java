package co.edu.itm.restaurant.orders.application.controllers;

import co.edu.itm.restaurant.orders.application.services.OrderService;
import co.edu.itm.restaurant.orders.domain.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //@GetMapping("/order")
    @GetMapping
    public String getOrder() {
        return "Order completado";
    }

    //@PostMapping("/order")
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    //@DeleteMapping("/order")
    @DeleteMapping
    public String deleteOrder() {
        return "Order completado";
    }


}
