package co.edu.itm.restaurant.orders.domain.repositories;

import co.edu.itm.restaurant.orders.domain.entities.OrderItem;
import co.edu.itm.restaurant.orders.infraestructure.persistence.IOrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemRepository {

    @Autowired
    private IOrderItemRepository iOrderItemRepository;

    public OrderItem  save(OrderItem orderItem) {
        return iOrderItemRepository.save(orderItem);
    }
}
