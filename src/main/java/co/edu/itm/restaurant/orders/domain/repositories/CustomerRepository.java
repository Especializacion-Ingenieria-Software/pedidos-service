package co.edu.itm.restaurant.orders.domain.repositories;


import co.edu.itm.restaurant.orders.domain.entities.Customer;
import co.edu.itm.restaurant.orders.infraestructure.persistence.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomerRepository {

    @Autowired
    private ICustomerRepository customerRepository;

    public Customer save(Customer customer) {
        customer.setId(UUID.randomUUID().hashCode());
        return customerRepository.save(customer);
    }

    public  Customer findById(Integer customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    public void delete(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

}
