package co.edu.itm.restaurant.orders.domain.entities;

import co.edu.itm.restaurant.orders.domain.value_objects.Address;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collation = "customers")
public class Customer {

    @Id
    private int id;
    private String name;
    private Address address;
    private String email;
    private String phone;

}
