package co.edu.itm.restaurant.orders.application.services;

import co.edu.itm.restaurant.orders.domain.entities.Customer;
import co.edu.itm.restaurant.orders.domain.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer();
        testCustomer.setId(1);
        testCustomer.setName("John Doe");
        testCustomer.setEmail("john.doe@example.com");
    }

    @Test
    void saveCustomer_ShouldReturnSavedCustomer() {
        // Arrange
        Customer customerToSave = new Customer();
        customerToSave.setName("Jane Doe");
        customerToSave.setEmail("jane.doe@example.com");

        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);

        // Act
        Customer savedCustomer = customerService.saveCustomer(customerToSave);

        // Assert
        assertNotNull(savedCustomer);
        assertEquals(1, savedCustomer.getId());
        assertEquals("John Doe", savedCustomer.getName());
        assertEquals("john.doe@example.com", savedCustomer.getEmail());
        verify(customerRepository, times(1)).save(customerToSave);
    }

    @Test
    void findCustomerById_WhenCustomerExists_ShouldReturnCustomer() {
        // Arrange
        Integer customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(testCustomer);

        // Act
        Customer foundCustomer = customerService.findCustomerById(customerId);

        // Assert
        assertNotNull(foundCustomer);
        assertEquals(1, foundCustomer.getId());
        assertEquals("John Doe", foundCustomer.getName());
        assertEquals("john.doe@example.com", foundCustomer.getEmail());
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    void findCustomerById_WhenCustomerDoesNotExist_ShouldReturnNull() {
        // Arrange
        Integer customerId = 99;
        when(customerRepository.findById(customerId)).thenReturn(null);

        // Act
        Customer foundCustomer = customerService.findCustomerById(customerId);

        // Assert
        assertNull(foundCustomer);
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    void deleteCustomer_ShouldCallRepositoryDelete() {
        // Arrange
        Integer customerId = 1;
        doNothing().when(customerRepository).delete(customerId);

        // Act
        customerService.deleteCustomer(customerId);

        // Assert
        verify(customerRepository, times(1)).delete(customerId);
    }

    @Test
    void deleteCustomer_WhenRepositoryThrowsException_ShouldPropagateException() {
        // Arrange
        Integer customerId = 1;
        doThrow(new RuntimeException("Error deleting customer")).when(customerRepository).delete(customerId);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            customerService.deleteCustomer(customerId);
        });

        assertEquals("Error deleting customer", exception.getMessage());
        verify(customerRepository, times(1)).delete(customerId);
    }
}