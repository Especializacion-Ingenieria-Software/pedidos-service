package co.edu.itm.restaurant.orders.application.services;

import co.edu.itm.restaurant.orders.application.dto.OrderDTO;
import co.edu.itm.restaurant.orders.application.dto.OrderItemDTO;
import co.edu.itm.restaurant.orders.domain.entities.Customer;
import co.edu.itm.restaurant.orders.domain.entities.Order;
import co.edu.itm.restaurant.orders.domain.entities.OrderItem;
import co.edu.itm.restaurant.orders.domain.repositories.CustomerRepository;
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
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Order saveOrder(OrderDTO orderDTO) {
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
        Order  order = new Order();
        order.setCustomer(orderDTO.getCustomer());
        order.setComments(orderDTO.getComments());
        order.setOrderTotal(orderDTO.getOrderTotal());
        order.setItems(orderItem.getId());
        return orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Integer id) {
        return orderRepository.findById(id);
    }

}
