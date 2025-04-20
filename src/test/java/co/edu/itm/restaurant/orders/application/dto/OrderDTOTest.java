package co.edu.itm.restaurant.orders.application.dto;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderDTOTest {

    @Test
    public void testEmptyConstructor() {
        // Act
        OrderDTO orderDTO = new OrderDTO();

        // Assert
        assertNotNull(orderDTO);
        assertNull(orderDTO.getId());
        assertNull(orderDTO.getComments());
        assertEquals(0.0, orderDTO.getOrderTotal());
        assertEquals(0, orderDTO.getCustomer());
        assertNull(orderDTO.getItems());
    }

    @Test
    public void testAllArgsConstructor() {
        // Arrange
        Integer id = 1;
        String comments = "Test order";
        double orderTotal = 100.0;
        int customer = 123;
        List<OrderItemDTO> items = new ArrayList<>();
        items.add(new OrderItemDTO());

        // Act
        OrderDTO orderDTO = new OrderDTO(id, comments, orderTotal, customer, items);

        // Assert
        assertEquals(id, orderDTO.getId());
        assertEquals(comments, orderDTO.getComments());
        assertEquals(orderTotal, orderDTO.getOrderTotal());
        assertEquals(customer, orderDTO.getCustomer());
        assertEquals(items, orderDTO.getItems());
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        Integer id = 1;
        String comments = "Test order";
        double orderTotal = 100.0;
        int customer = 123;
        List<OrderItemDTO> items = new ArrayList<>();
        items.add(new OrderItemDTO());

        // Act
        orderDTO.setId(id);
        orderDTO.setComments(comments);
        orderDTO.setOrderTotal(orderTotal);
        orderDTO.setCustomer(customer);
        orderDTO.setItems(items);

        // Assert
        assertEquals(id, orderDTO.getId());
        assertEquals(comments, orderDTO.getComments());
        assertEquals(orderTotal, orderDTO.getOrderTotal());
        assertEquals(customer, orderDTO.getCustomer());
        assertEquals(items, orderDTO.getItems());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Arrange
        OrderDTO orderDTO1 = new OrderDTO(1, "Test order", 100.0, 123, new ArrayList<>());
        OrderDTO orderDTO2 = new OrderDTO(1, "Test order", 100.0, 123, new ArrayList<>());
        OrderDTO orderDTO3 = new OrderDTO(2, "Another order", 200.0, 456, new ArrayList<>());

        // Assert - equals
        assertEquals(orderDTO1, orderDTO1); // Reflexivity
        assertEquals(orderDTO1, orderDTO2); // Symmetry
        assertNotEquals(orderDTO1, orderDTO3);
        assertNotEquals(orderDTO1, null);
        assertNotEquals(orderDTO1, "Not an OrderDTO");

        // Assert - hashCode
        assertEquals(orderDTO1.hashCode(), orderDTO2.hashCode());
        assertNotEquals(orderDTO1.hashCode(), orderDTO3.hashCode());
    }

    @Test
    public void testToString() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO(1, "Test order", 100.0, 123, new ArrayList<>());

        // Act
        String toStringResult = orderDTO.toString();

        // Assert
        assertTrue(toStringResult.contains("id=1"));
        assertTrue(toStringResult.contains("comments=Test order"));
        assertTrue(toStringResult.contains("orderTotal=100.0"));
        assertTrue(toStringResult.contains("customer=123"));
        assertTrue(toStringResult.contains("items=[]"));
    }

    @Test
    public void testEqualsWithDifferentItemLists() {
        // Arrange
        List<OrderItemDTO> items1 = new ArrayList<>();
        items1.add(new OrderItemDTO());

        List<OrderItemDTO> items2 = new ArrayList<>();
        items2.add(new OrderItemDTO());
        items2.add(new OrderItemDTO());

        OrderDTO orderDTO1 = new OrderDTO(1, "Test order", 100.0, 123, items1);
        OrderDTO orderDTO2 = new OrderDTO(1, "Test order", 100.0, 123, items2);

        // Assert
        assertNotEquals(orderDTO1, orderDTO2);
    }
}