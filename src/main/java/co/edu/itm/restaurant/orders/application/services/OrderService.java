package co.edu.itm.restaurant.orders.application.services;

import co.edu.itm.restaurant.orders.application.dto.OrderDTO;
import co.edu.itm.restaurant.orders.domain.entities.Order;
import co.edu.itm.restaurant.orders.domain.repositories.OrderItemRepository;
import co.edu.itm.restaurant.orders.domain.services.OrderHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {

    @Autowired
    private OrderHandleService orderHandleService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderDTO saveOrder(OrderDTO order) {
        return orderHandleService.saveOrder(order);
    }

    public List<Order> findAll() {
        return orderHandleService.findAll();
    }

    public Order findById(Integer id) {
        return orderHandleService.findById(id);
    }

}
