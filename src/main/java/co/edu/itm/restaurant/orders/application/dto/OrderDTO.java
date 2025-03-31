package co.edu.itm.restaurant.orders.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {

    private Integer id;
    private String comments;
    private double orderTotal;
    private int customer;
    private List<OrderItemDTO> items;
}
