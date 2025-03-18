package co.edu.itm.restaurant.orders.infraestructure.persistence;

import co.edu.itm.restaurant.orders.domain.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IOrderRepository extends MongoRepository<Order, Integer> {

}
