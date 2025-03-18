package co.edu.itm.restaurant.orders.domain.repositories;

import co.edu.itm.restaurant.orders.domain.entities.Order;
import co.edu.itm.restaurant.orders.infraestructure.persistence.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderRepository {

    @Autowired
    private IOrderRepository orderRepository;

    public Order save(Order order) {
        order.setId(UUID.randomUUID().hashCode());
        return orderRepository.save(order);
    }

}
