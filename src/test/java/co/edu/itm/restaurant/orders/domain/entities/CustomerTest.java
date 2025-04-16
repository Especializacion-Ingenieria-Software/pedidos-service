package co.edu.itm.restaurant.orders.domain.entities;


import co.edu.itm.restaurant.orders.domain.value_objects.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private Customer customer;
    private Address address;

    @BeforeEach
    void setUp() {
        // Configurar una dirección para las pruebas con la estructura correcta
        address = new Address("Poblado", "Medellín", "Calle 10 # 43-12", "Edificio Plaza, Apto 502");

        // Crear un cliente con valores iniciales
        customer = new Customer(1, "Juan Pérez", address, "juan@example.com", "3001234567");
    }

    @Test
    void testCustomerCreation() {
        assertNotNull(customer);
        assertEquals(1, customer.getId());
        assertEquals("Juan Pérez", customer.getName());
        assertEquals(address, customer.getAddress());
        assertEquals("juan@example.com", customer.getEmail());
        assertEquals("3001234567", customer.getPhone());
    }

    @Test
    void testCustomerSetters() {
        // Modificar propiedades
        Address newAddress = new Address("Centro", "Bogotá", "Carrera 7 # 32-15", "Torre Empresarial, Piso 8");
        customer.setId(2);
        customer.setName("María López");
        customer.setAddress(newAddress);
        customer.setEmail("maria@example.com");
        customer.setPhone("3119876543");

        // Verificar cambios
        assertEquals(2, customer.getId());
        assertEquals("María López", customer.getName());
        assertEquals(newAddress, customer.getAddress());
        assertEquals("maria@example.com", customer.getEmail());
        assertEquals("3119876543", customer.getPhone());
    }

    @Test
    void testEqualsAndHashCode() {
        // Crear un cliente idéntico
        Customer sameCustomer = new Customer(1, "Juan Pérez", address, "juan@example.com", "3001234567");

        // Verificar equals y hashCode
        assertEquals(customer, sameCustomer);
        assertEquals(customer.hashCode(), sameCustomer.hashCode());

        // Crear un cliente diferente
        Customer differentCustomer = new Customer(2, "Pedro Gómez", address, "pedro@example.com", "3207654321");

        // Verificar que no son iguales
        assertNotEquals(customer, differentCustomer);
    }

    @Test
    void testToString() {
        String expectedString = customer.toString();
        assertTrue(expectedString.contains("Juan Pérez"));
        assertTrue(expectedString.contains("juan@example.com"));
        assertTrue(expectedString.contains("3001234567"));
    }

    @Test
    void testNoArgsConstructor() {
        Customer emptyCustomer = new Customer();
        assertNull(emptyCustomer.getId());
        assertNull(emptyCustomer.getName());
        assertNull(emptyCustomer.getAddress());
        assertNull(emptyCustomer.getEmail());
        assertNull(emptyCustomer.getPhone());
    }

    @Test
    void testAddressInCustomer() {
        // Verificar que la dirección se guarda correctamente
        assertEquals("Poblado", customer.getAddress().getTown());
        assertEquals("Medellín", customer.getAddress().getCity());
        assertEquals("Calle 10 # 43-12", customer.getAddress().getCompositeAddress());
        assertEquals("Edificio Plaza, Apto 502", customer.getAddress().getAddressDetails());

        // Modificar la dirección y verificar cambios
        Address updatedAddress = new Address("Laureles", "Medellín", "Circular 76 # 39-45", "Casa 12");
        customer.setAddress(updatedAddress);

        assertEquals("Laureles", customer.getAddress().getTown());
        assertEquals("Medellín", customer.getAddress().getCity());
        assertEquals("Circular 76 # 39-45", customer.getAddress().getCompositeAddress());
        assertEquals("Casa 12", customer.getAddress().getAddressDetails());
    }
}
