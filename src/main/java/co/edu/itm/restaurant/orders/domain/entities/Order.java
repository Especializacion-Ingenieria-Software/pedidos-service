package co.edu.itm.restaurant.orders.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collation = "orders")
public class Order {

    @Id
    private Integer id;
    private String comments;
    private double orderTotal;
    private int customer;
    private List<OrderItem> items;

}
