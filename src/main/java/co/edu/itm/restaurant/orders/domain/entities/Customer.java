package co.edu.itm.restaurant.orders.domain.entities;

import co.edu.itm.restaurant.orders.domain.value_objects.Address;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "customers")
public class Customer {

    @MongoId
    private Integer _id;
    private String name;
    private Address address;
    private String email;
    private String phone;

}
