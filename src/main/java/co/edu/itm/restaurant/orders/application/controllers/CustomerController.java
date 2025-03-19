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

    @GetMapping
    public String getCustomer() {
        return "Customer completado";
    }


    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {

        return customerService.saveCustomer(customer);
    }


    @DeleteMapping
    public String deleteCustomer() {
        return "Delete customer completado";
    }


}
