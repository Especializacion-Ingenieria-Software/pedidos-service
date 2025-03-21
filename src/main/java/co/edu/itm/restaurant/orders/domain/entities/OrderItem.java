package co.edu.itm.restaurant.orders.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collation = "orderItem")
public class OrderItem {

    @Id
    private int id;

    private int amount;
    private double unitPrice;

}
