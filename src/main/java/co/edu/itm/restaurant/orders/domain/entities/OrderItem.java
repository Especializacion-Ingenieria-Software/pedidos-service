package co.edu.itm.restaurant.orders.domain.entities;


import co.edu.itm.restaurant.orders.domain.value_objects.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "orderItem")
public class OrderItem {

    @Id
    private int id;
    private List<Item> item;

}
