package co.edu.itm.restaurant.orders.domain.value_objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

    private String town;
    private String city;
    private String compositeAddress;
    private String addressDetails;

}
