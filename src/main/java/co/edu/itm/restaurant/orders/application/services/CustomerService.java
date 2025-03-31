package co.edu.itm.restaurant.orders.application.services;

import co.edu.itm.restaurant.orders.domain.entities.Customer;
import co.edu.itm.restaurant.orders.domain.entities.Order;
import co.edu.itm.restaurant.orders.domain.repositories.CustomerRepository;
import co.edu.itm.restaurant.orders.domain.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findCustomerById(Integer customerId) {
        return customerRepository.findById(customerId);
    }

    public void deleteCustomer(Integer customerId) {
        customerRepository.delete(customerId);
    }
}
