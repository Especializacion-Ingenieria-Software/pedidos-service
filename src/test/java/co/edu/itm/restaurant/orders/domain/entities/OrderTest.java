package co.edu.itm.restaurant.orders.domain.entities;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        // Crear una orden con valores iniciales para cada prueba
        order = new Order(1, "Entregar en la puerta principal", 45.75, 101, 3);
    }

    @Test
    void testOrderCreation() {
        // Verificar que el constructor con argumentos funciona correctamente
        assertNotNull(order);
        assertEquals(1, order.getId());
        assertEquals("Entregar en la puerta principal", order.getComments());
        assertEquals(45.75, order.getOrderTotal(), 0.001); // Usando delta para comparación de double
        assertEquals(101, order.getCustomer());
        assertEquals(3, order.getItems());
    }

    @Test
    void testOrderSetters() {
        // Verificar que los setters funcionan correctamente
        order.setId(2);
        order.setComments("Llamar al llegar");
        order.setOrderTotal(89.99);
        order.setCustomer(202);
        order.setItems(5);

        // Comprobar que los valores han sido actualizados
        assertEquals(2, order.getId());
        assertEquals("Llamar al llegar", order.getComments());
        assertEquals(89.99, order.getOrderTotal(), 0.001);
        assertEquals(202, order.getCustomer());
        assertEquals(5, order.getItems());
    }

    @Test
    void testNoArgsConstructor() {
        // Verificar que el constructor sin argumentos funciona correctamente
        Order emptyOrder = new Order();

        // Verificar que todos los campos son nulos o tienen valores por defecto
        assertNull(emptyOrder.getId());
        assertNull(emptyOrder.getComments());
        assertEquals(0.0, emptyOrder.getOrderTotal(), 0.001);
        assertEquals(0, emptyOrder.getCustomer());
        assertNull(emptyOrder.getItems());
    }

    @Test
    void testEqualsAndHashCode() {
        // Crear una orden con los mismos datos
        Order sameOrder = new Order(1, "Entregar en la puerta principal", 45.75, 101, 3);

        // Verificar que equals y hashCode funcionan correctamente
        assertEquals(order, sameOrder);
        assertEquals(order.hashCode(), sameOrder.hashCode());

        // Crear una orden con datos diferentes
        Order differentOrder = new Order(2, "Sin comentarios", 29.99, 102, 2);

        // Verificar que no son iguales
        assertNotEquals(order, differentOrder);
        assertNotEquals(order.hashCode(), differentOrder.hashCode());
    }

    @Test
    void testToString() {
        // Verificar que toString contiene la información relevante
        String orderString = order.toString();

        assertTrue(orderString.contains("id=1"));
        assertTrue(orderString.contains("comments=Entregar en la puerta principal"));
        assertTrue(orderString.contains("orderTotal=45.75"));
        assertTrue(orderString.contains("customer=101"));
        assertTrue(orderString.contains("items=3"));
    }

    @Test
    void testOrderTotalCalculation() {
        // Comprobar cálculos con el total del pedido
        order.setOrderTotal(100.00);
        assertEquals(100.00, order.getOrderTotal(), 0.001);

        // Comprobar actualización del total
        order.setOrderTotal(order.getOrderTotal() + 20.50);
        assertEquals(120.50, order.getOrderTotal(), 0.001);
    }
}