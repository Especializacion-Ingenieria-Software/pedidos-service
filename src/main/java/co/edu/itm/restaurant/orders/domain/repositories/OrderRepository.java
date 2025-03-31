package co.edu.itm.restaurant.orders.domain.repositories;

import co.edu.itm.restaurant.orders.domain.entities.Customer;
import co.edu.itm.restaurant.orders.domain.entities.Order;
import co.edu.itm.restaurant.orders.infraestructure.persistence.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class OrderRepository {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Order save(Order order) {
        try {
            Customer customer = customerRepository.findById(order.getCustomer());
            System.out.println(customer.getId());
        }
        catch (Exception e) {
            System.err.println("No se encontro usuario");
            return  null;
        }
        order.setId(UUID.randomUUID().hashCode());
        return orderRepository.save(order);
    }

    public Order findById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
