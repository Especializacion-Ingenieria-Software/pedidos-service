package co.edu.itm.restaurant.orders.application.controllers;

import co.edu.itm.restaurant.orders.application.services.CustomerService;
import co.edu.itm.restaurant.orders.domain.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable Integer customerId) {
        return customerService.findCustomerById(customerId);
    }


    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }


    @DeleteMapping("/{customerId}" )
    public void deleteCustomer(@PathVariable Integer customerId) {
        customerService.deleteCustomer(customerId);
    }

}
