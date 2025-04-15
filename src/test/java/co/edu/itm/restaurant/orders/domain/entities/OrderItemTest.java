package co.edu.itm.restaurant.orders.domain.entities;

import co.edu.itm.restaurant.orders.domain.value_objects.Item;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderItemTest {

    @Test
    public void testCreateEmptyOrderItem() {
        OrderItem orderItem = new OrderItem();
        assertNotNull(orderItem);
        assertNull(orderItem.getItem());
        assertEquals(0, orderItem.getId());
    }

    @Test
    public void testCreateOrderItemWithParams() {
        // Crear items
        Item item1 = new Item(1, "Pizza", 2, 10.5);
        Item item2 = new Item(2, "Hamburger", 1, 8.75);
        List<Item> items = Arrays.asList(item1, item2);

        // Crear OrderItem con parámetros
        OrderItem orderItem = new OrderItem(1, items);

        // Verificar
        assertEquals(1, orderItem.getId());
        assertNotNull(orderItem.getItem());
        assertEquals(2, orderItem.getItem().size());
        assertEquals("Pizza", orderItem.getItem().get(0).getName());
        assertEquals("Hamburger", orderItem.getItem().get(1).getName());
    }

    @Test
    public void testSetAndGetId() {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(5);
        assertEquals(5, orderItem.getId());
    }

    @Test
    public void testSetAndGetItems() {
        // Crear OrderItem vacío
        OrderItem orderItem = new OrderItem();

        // Crear y asignar items
        Item item1 = new Item(1, "Soda", 3, 2.5);
        Item item2 = new Item(2, "Fries", 2, 3.75);
        List<Item> items = Arrays.asList(item1, item2);

        orderItem.setItem(items);

        // Verificar
        List<Item> retrievedItems = orderItem.getItem();
        assertNotNull(retrievedItems);
        assertEquals(2, retrievedItems.size());
        assertEquals("Soda", retrievedItems.get(0).getName());
        assertEquals(3, retrievedItems.get(0).getAmount());
        assertEquals(2.5, retrievedItems.get(0).getUnitPrice());
        assertEquals("Fries", retrievedItems.get(1).getName());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Crear dos OrderItems iguales
        Item item1 = new Item(1, "Pizza", 1, 10.5);
        List<Item> items1 = Arrays.asList(item1);
        OrderItem orderItem1 = new OrderItem(1, items1);

        Item item2 = new Item(1, "Pizza", 1, 10.5);
        List<Item> items2 = Arrays.asList(item2);
        OrderItem orderItem2 = new OrderItem(1, items2);

        // Verificar equals y hashCode
        assertEquals(orderItem1, orderItem2);
        assertEquals(orderItem1.hashCode(), orderItem2.hashCode());

        // Modificar un OrderItem y verificar que ya no son iguales
        orderItem2.setId(2);
        assertNotEquals(orderItem1, orderItem2);
        assertNotEquals(orderItem1.hashCode(), orderItem2.hashCode());
    }

    @Test
    public void testToString() {
        // Crear OrderItem
        Item item = new Item(1, "Pizza", 1, 10.5);
        List<Item> items = Arrays.asList(item);
        OrderItem orderItem = new OrderItem(1, items);

        // Verificar toString
        String toStringResult = orderItem.toString();
        assertTrue(toStringResult.contains("id=1"));
        assertTrue(toStringResult.contains("Pizza"));
        assertTrue(toStringResult.contains("10.5"));
    }

    @Test
    public void testModifyItemsList() {
        // Crear OrderItem con lista vacía
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(new ArrayList<>());

        // Agregar items a la lista
        List<Item> items = orderItem.getItem();
        items.add(new Item(1, "Pizza", 1, 10.5));
        items.add(new Item(2, "Soda", 2, 2.5));

        // Verificar que se han agregado los items
        assertEquals(2, orderItem.getItem().size());
        assertEquals("Pizza", orderItem.getItem().get(0).getName());
        assertEquals("Soda", orderItem.getItem().get(1).getName());

        // Eliminar un item
        items.remove(0);

        // Verificar que se ha eliminado el item
        assertEquals(1, orderItem.getItem().size());
        assertEquals("Soda", orderItem.getItem().get(0).getName());
    }

    @Test
    public void testModifyItemProperties() {
        // Crear item y OrderItem
        Item item = new Item(1, "Pizza", 1, 10.5);
        List<Item> items = new ArrayList<>();
        items.add(item);
        OrderItem orderItem = new OrderItem(1, items);

        // Modificar propiedades del item
        orderItem.getItem().get(0).setAmount(2);
        orderItem.getItem().get(0).setUnitPrice(12.75);

        // Verificar cambios
        assertEquals(2, orderItem.getItem().get(0).getAmount());
        assertEquals(12.75, orderItem.getItem().get(0).getUnitPrice());
    }
}