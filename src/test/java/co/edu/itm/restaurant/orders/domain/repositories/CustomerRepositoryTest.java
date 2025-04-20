package co.edu.itm.restaurant.orders.domain.repositories;

import co.edu.itm.restaurant.orders.domain.entities.Customer;
import co.edu.itm.restaurant.orders.infraestructure.persistence.ICustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerRepositoryTest {

    @Mock
    private ICustomerRepository iCustomerRepository;

    @InjectMocks
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave_AssignsIdAndSavesCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john@example.com");

        when(iCustomerRepository.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Customer savedCustomer = customerRepository.save(customer);

        // Assert
        assertNotNull(savedCustomer);
        assertNotNull(savedCustomer.getId());
        assertEquals("John Doe", savedCustomer.getName());
        assertEquals("john@example.com", savedCustomer.getEmail());
        verify(iCustomerRepository, times(1)).save(customer);
    }

    @Test
    public void testSave_IdIsAssignedEvenIfCustomerAlreadyHasOne() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(123);
        customer.setName("John Doe");

        when(iCustomerRepository.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Customer savedCustomer = customerRepository.save(customer);

        // Assert
        assertNotNull(savedCustomer);
        assertNotNull(savedCustomer.getId());
        assertNotEquals(123, savedCustomer.getId()); // ID debería haber cambiado
        verify(iCustomerRepository, times(1)).save(customer);
    }

    @Test
    public void testFindById_WhenCustomerExists_ReturnsCustomer() {
        // Arrange
        Integer customerId = 123;
        Customer expectedCustomer = new Customer();
        expectedCustomer.setId(customerId);
        expectedCustomer.setName("John Doe");

        when(iCustomerRepository.findById(customerId)).thenReturn(Optional.of(expectedCustomer));

        // Act
        Customer foundCustomer = customerRepository.findById(customerId);

        // Assert
        assertNotNull(foundCustomer);
        assertEquals(customerId, foundCustomer.getId());
        assertEquals("John Doe", foundCustomer.getName());
        verify(iCustomerRepository, times(1)).findById(customerId);
    }

    @Test
    public void testFindById_WhenCustomerDoesNotExist_ReturnsNull() {
        // Arrange
        Integer customerId = 123;
        when(iCustomerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act
        Customer foundCustomer = customerRepository.findById(customerId);

        // Assert
        assertNull(foundCustomer);
        verify(iCustomerRepository, times(1)).findById(customerId);
    }

    @Test
    public void testDelete_CallsDeleteById() {
        // Arrange
        Integer customerId = 123;
        doNothing().when(iCustomerRepository).deleteById(customerId);

        // Act
        customerRepository.delete(customerId);

        // Assert
        verify(iCustomerRepository, times(1)).deleteById(customerId);
    }

    @Test
    public void testDelete_WhenRepositoryThrowsException_PropagatesException() {
        // Arrange
        Integer customerId = 123;
        doThrow(new RuntimeException("Not found")).when(iCustomerRepository).deleteById(customerId);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            customerRepository.delete(customerId);
        });
        verify(iCustomerRepository, times(1)).deleteById(customerId);
    }
}