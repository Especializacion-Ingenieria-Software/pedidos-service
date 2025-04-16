package co.edu.itm.restaurant.orders.domain.value_objects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    @Test
    public void testCreateEmptyItem() {
        Item item = new Item();
        assertNotNull(item);
        assertNull(item.getId());
        assertNull(item.getName());
        assertEquals(0, item.getAmount());
        assertEquals(0.0, item.getUnitPrice());
    }

    @Test
    public void testCreateItemWithConstructor() {
        Item item = new Item(1, "Pizza", 2, 10.5);

        assertEquals(1, item.getId());
        assertEquals("Pizza", item.getName());
        assertEquals(2, item.getAmount());
        assertEquals(10.5, item.getUnitPrice());
    }

    @Test
    public void testSettersAndGetters() {
        Item item = new Item();

        item.setId(2);
        item.setName("Soda");
        item.setAmount(3);
        item.setUnitPrice(2.5);

        assertEquals(2, item.getId());
        assertEquals("Soda", item.getName());
        assertEquals(3, item.getAmount());
        assertEquals(2.5, item.getUnitPrice());
    }

    @Test
    public void testEqualsAndHashCode() {
        Item item1 = new Item(1, "Pizza", 2, 10.5);
        Item item2 = new Item(1, "Pizza", 2, 10.5);
        Item item3 = new Item(2, "Soda", 1, 2.0);

        // Verificar equals
        assertEquals(item1, item2);
        assertNotEquals(item1, item3);

        // Verificar hashCode
        assertEquals(item1.hashCode(), item2.hashCode());
        assertNotEquals(item1.hashCode(), item3.hashCode());
    }

    @Test
    public void testToString() {
        Item item = new Item(1, "Pizza", 2, 10.5);
        String toString = item.toString();

        // Verificar que toString contiene la información relevante
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("name=Pizza"));
        assertTrue(toString.contains("amount=2"));
        assertTrue(toString.contains("unitPrice=10.5"));
    }

    @Test
    public void testEqualsWithNull() {
        Item item = new Item(1, "Pizza", 2, 10.5);

        // Un objeto no debería ser igual a null
        assertNotEquals(null, item);
    }

    @Test
    public void testEqualsWithDifferentClass() {
        Item item = new Item(1, "Pizza", 2, 10.5);
        String differentClass = "Not an Item";

        // Un objeto no debería ser igual a una instancia de otra clase
        assertNotEquals(differentClass, item);
    }

    @Test
    public void testPartialEquality() {
        Item item1 = new Item(1, "Pizza", 2, 10.5);
        Item item2 = new Item(1, "Pizza", 2, 11.5);

        // Estos objetos deberían ser diferentes por tener unitPrice diferente
        assertNotEquals(item1, item2);

        // Modificar item2 para hacerlo igual a item1
        item2.setUnitPrice(10.5);
        assertEquals(item1, item2);
    }

    @Test
    public void testCalculateTotalPrice() {
        Item item = new Item(1, "Pizza", 3, 10.5);

        // Cálculo manual del precio total
        double expectedTotal = 3 * 10.5;
        double actualTotal = item.getAmount() * item.getUnitPrice();

        assertEquals(expectedTotal, actualTotal);
    }
}