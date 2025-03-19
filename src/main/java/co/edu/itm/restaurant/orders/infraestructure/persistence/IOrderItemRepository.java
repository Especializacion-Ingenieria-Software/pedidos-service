package co.edu.itm.restaurant.orders.infraestructure.persistence;

import co.edu.itm.restaurant.orders.domain.entities.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IOrderItemRepository extends MongoRepository<OrderItem, Integer> {

}
