package co.edu.itm.restaurant.orders.domain.services;

import co.edu.itm.restaurant.orders.application.dto.OrderDTO;
import co.edu.itm.restaurant.orders.application.dto.OrderItemDTO;
import co.edu.itm.restaurant.orders.domain.entities.Order;
import co.edu.itm.restaurant.orders.domain.entities.OrderItem;
import co.edu.itm.restaurant.orders.domain.repositories.OrderItemRepository;
import co.edu.itm.restaurant.orders.domain.repositories.OrderRepository;
import co.edu.itm.restaurant.orders.domain.value_objects.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class OrderHandleService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(UUID.randomUUID().hashCode());
        orderItem.setItem(new ArrayList<>());
        for (OrderItemDTO oi : orderDTO.getItems()) {
            Item item = new Item();
            item.setId(UUID.randomUUID().hashCode());
            item.setName(oi.getName());
            item.setAmount(oi.getAmount());
            item.setUnitPrice(oi.getUnitPrice());
            orderItem.getItem().add(item);
        }
        orderItem = orderItemRepository.save(orderItem);
        Order order = new Order();
        order.setCustomer(orderDTO.getCustomer());
        order.setComments(orderDTO.getComments());
        order.setOrderTotal(orderDTO.getOrderTotal());
        order.setItems(orderItem.getId());
         int id = orderRepository.save(order).getId();
         orderDTO.setId(id);
         return orderDTO;
    }

    public Order findById(Integer id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders;
    }
}
