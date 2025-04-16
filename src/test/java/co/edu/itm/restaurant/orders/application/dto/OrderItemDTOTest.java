package co.edu.itm.restaurant.orders.application.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderItemDTOTest {

    @Test
    public void testEmptyConstructor() {
        // Act
        OrderItemDTO orderItemDTO = new OrderItemDTO();

        // Assert
        assertNotNull(orderItemDTO);
        assertNull(orderItemDTO.getId());
        assertNull(orderItemDTO.getName());
        assertEquals(0, orderItemDTO.getAmount());
        assertEquals(0.0, orderItemDTO.getUnitPrice());
    }

    @Test
    public void testAllArgsConstructor() {
        // Arrange
        Integer id = 1;
        String name = "Pizza";
        int amount = 2;
        double unitPrice = 10.5;

        // Act
        OrderItemDTO orderItemDTO = new OrderItemDTO(id, name, amount, unitPrice);

        // Assert
        assertEquals(id, orderItemDTO.getId());
        assertEquals(name, orderItemDTO.getName());
        assertEquals(amount, orderItemDTO.getAmount());
        assertEquals(unitPrice, orderItemDTO.getUnitPrice());
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        Integer id = 1;
        String name = "Pizza";
        int amount = 2;
        double unitPrice = 10.5;

        // Act
        orderItemDTO.setId(id);
        orderItemDTO.setName(name);
        orderItemDTO.setAmount(amount);
        orderItemDTO.setUnitPrice(unitPrice);

        // Assert
        assertEquals(id, orderItemDTO.getId());
        assertEquals(name, orderItemDTO.getName());
        assertEquals(amount, orderItemDTO.getAmount());
        assertEquals(unitPrice, orderItemDTO.getUnitPrice());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Arrange
        OrderItemDTO orderItemDTO1 = new OrderItemDTO(1, "Pizza", 2, 10.5);
        OrderItemDTO orderItemDTO2 = new OrderItemDTO(1, "Pizza", 2, 10.5);
        OrderItemDTO orderItemDTO3 = new OrderItemDTO(2, "Soda", 1, 2.0);

        // Assert - equals
        assertEquals(orderItemDTO1, orderItemDTO1); // Reflexivity
        assertEquals(orderItemDTO1, orderItemDTO2); // Symmetry
        assertNotEquals(orderItemDTO1, orderItemDTO3);
        assertNotEquals(orderItemDTO1, null);
        assertNotEquals(orderItemDTO1, "Not an OrderItemDTO");

        // Assert - hashCode
        assertEquals(orderItemDTO1.hashCode(), orderItemDTO2.hashCode());
        assertNotEquals(orderItemDTO1.hashCode(), orderItemDTO3.hashCode());
    }

    @Test
    public void testToString() {
        // Arrange
        OrderItemDTO orderItemDTO = new OrderItemDTO(1, "Pizza", 2, 10.5);

        // Act
        String toStringResult = orderItemDTO.toString();

        // Assert
        assertTrue(toStringResult.contains("id=1"));
        assertTrue(toStringResult.contains("name=Pizza"));
        assertTrue(toStringResult.contains("amount=2"));
        assertTrue(toStringResult.contains("unitPrice=10.5"));
    }

    @Test
    public void testPartialEquality() {
        // Arrange
        OrderItemDTO orderItemDTO1 = new OrderItemDTO(1, "Pizza", 2, 10.5);
        OrderItemDTO orderItemDTO2 = new OrderItemDTO(1, "Pizza", 2, 11.5); // Diferente precio

        // Assert
        assertNotEquals(orderItemDTO1, orderItemDTO2);

        // Act - Hacer iguales
        orderItemDTO2.setUnitPrice(10.5);

        // Assert
        assertEquals(orderItemDTO1, orderItemDTO2);
    }

    @Test
    public void testCalculateTotalPrice() {
        // Arrange
        OrderItemDTO orderItemDTO = new OrderItemDTO(1, "Pizza", 3, 10.5);

        // Act - Cálculo manual del precio total
        double expectedTotal = 3 * 10.5;
        double actualTotal = orderItemDTO.getAmount() * orderItemDTO.getUnitPrice();

        // Assert
        assertEquals(expectedTotal, actualTotal);
    }
}