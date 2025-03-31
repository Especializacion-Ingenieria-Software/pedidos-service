package co.edu.itm.restaurant.orders.application.controllers;

import co.edu.itm.restaurant.orders.application.dto.OrderDTO;
import co.edu.itm.restaurant.orders.application.services.OrderService;
import co.edu.itm.restaurant.orders.domain.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable Integer orderId) {
        return orderService.findById(orderId);
    }

    @PostMapping
public OrderDTO createOrder(@RequestBody OrderDTO order) {
        return orderService.saveOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }


}
