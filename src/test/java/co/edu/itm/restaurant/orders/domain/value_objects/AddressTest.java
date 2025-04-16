package co.edu.itm.restaurant.orders.domain.value_objects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    @Test
    public void testCreateEmptyAddress() {
        Address address = new Address();
        assertNotNull(address);
        assertNull(address.getTown());
        assertNull(address.getCity());
        assertNull(address.getCompositeAddress());
        assertNull(address.getAddressDetails());
    }

    @Test
    public void testCreateAddressWithConstructor() {
        Address address = new Address("Springfield", "Capital City", "742 Evergreen Terrace", "Apartment 2B");

        assertEquals("Springfield", address.getTown());
        assertEquals("Capital City", address.getCity());
        assertEquals("742 Evergreen Terrace", address.getCompositeAddress());
        assertEquals("Apartment 2B", address.getAddressDetails());
    }

    @Test
    public void testSettersAndGetters() {
        Address address = new Address();

        address.setTown("Medellin");
        address.setCity("Antioquia");
        address.setCompositeAddress("Calle 10 # 20-30");
        address.setAddressDetails("Edificio Torre Norte, Apartamento 501");

        assertEquals("Medellin", address.getTown());
        assertEquals("Antioquia", address.getCity());
        assertEquals("Calle 10 # 20-30", address.getCompositeAddress());
        assertEquals("Edificio Torre Norte, Apartamento 501", address.getAddressDetails());
    }

    @Test
    public void testEqualsAndHashCode() {
        Address address1 = new Address("Medellin", "Antioquia", "Calle 10 # 20-30", "Edificio A");
        Address address2 = new Address("Medellin", "Antioquia", "Calle 10 # 20-30", "Edificio A");
        Address address3 = new Address("Bogota", "Cundinamarca", "Carrera 7 # 71-21", "Oficina 505");

        // Verificar equals
        assertEquals(address1, address2);
        assertNotEquals(address1, address3);

        // Verificar hashCode
        assertEquals(address1.hashCode(), address2.hashCode());
        assertNotEquals(address1.hashCode(), address3.hashCode());
    }

    @Test
    public void testToString() {
        Address address = new Address("Medellin", "Antioquia", "Calle 10 # 20-30", "Edificio A");
        String toString = address.toString();

        // Verificar que toString contiene la información relevante
        assertTrue(toString.contains("town=Medellin"));
        assertTrue(toString.contains("city=Antioquia"));
        assertTrue(toString.contains("compositeAddress=Calle 10 # 20-30"));
        assertTrue(toString.contains("addressDetails=Edificio A"));
    }

    @Test
    public void testEqualsWithNull() {
        Address address = new Address("Medellin", "Antioquia", "Calle 10 # 20-30", "Edificio A");

        // Un objeto no debería ser igual a null
        assertNotEquals(null, address);
    }

    @Test
    public void testEqualsWithDifferentClass() {
        Address address = new Address("Medellin", "Antioquia", "Calle 10 # 20-30", "Edificio A");
        String differentClass = "Not an Address";

        // Un objeto no debería ser igual a una instancia de otra clase
        assertNotEquals(differentClass, address);
    }

    @Test
    public void testPartialEquality() {
        Address address1 = new Address("Medellin", "Antioquia", "Calle 10 # 20-30", "Edificio A");
        Address address2 = new Address("Medellin", "Antioquia", "Calle 10 # 20-30", "Edificio B");

        // Estos objetos deberían ser diferentes por tener addressDetails diferentes
        assertNotEquals(address1, address2);

        // Modificar address2 para hacerlo igual a address1
        address2.setAddressDetails("Edificio A");
        assertEquals(address1, address2);
    }
}