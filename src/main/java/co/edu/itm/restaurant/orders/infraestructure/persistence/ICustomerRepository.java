package co.edu.itm.restaurant.orders.infraestructure.persistence;

import co.edu.itm.restaurant.orders.domain.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICustomerRepository extends MongoRepository<Customer, Integer> {

}
