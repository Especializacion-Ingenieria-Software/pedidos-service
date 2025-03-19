package co.edu.itm.restaurant.orders.application.dto;

import co.edu.itm.restaurant.orders.domain.value_objects.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItemDTO {

    private Integer id;
    private String name;
    private int amount;
    private double unitPrice;

}
