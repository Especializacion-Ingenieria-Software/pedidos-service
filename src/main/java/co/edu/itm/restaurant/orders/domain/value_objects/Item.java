package co.edu.itm.restaurant.orders.domain.value_objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Item {

    private Integer id;
    private String name;
    private int amount;
    private double unitPrice;

}
