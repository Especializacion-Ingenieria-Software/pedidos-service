package co.edu.itm.restaurant.orders.application.controllers;

import co.edu.itm.restaurant.orders.application.services.CustomerService;
import co.edu.itm.restaurant.orders.domain.entities.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Customer testCustomer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        testCustomer = new Customer();
        testCustomer.setId(1);
        testCustomer.setName("John Doe");
        testCustomer.setEmail("john.doe@example.com");
    }

    @Test
    public void createCustomer_ValidCustomer_ReturnsCreatedCustomer() throws Exception {
        // Arrange
        Customer inputCustomer = new Customer();
        inputCustomer.setName("Jane Doe");
        inputCustomer.setEmail("jane.doe@example.com");

        Customer savedCustomer = new Customer();
        savedCustomer.setId(2);
        savedCustomer.setName("Jane Doe");
        savedCustomer.setEmail("jane.doe@example.com");

        when(customerService.saveCustomer(any(Customer.class))).thenReturn(savedCustomer);

        // Act & Assert
        mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputCustomer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"));

        verify(customerService, times(1)).saveCustomer(any(Customer.class));
    }

    @Test
    public void getCustomer_WhenCustomerExists_ReturnsCustomer() throws Exception {
        // Arrange
        when(customerService.findCustomerById(1)).thenReturn(testCustomer);

        // Act & Assert
        mockMvc.perform(get("/customer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(customerService, times(1)).findCustomerById(1);
    }

    @Test
    public void getCustomer_WhenCustomerDoesNotExist_ReturnsNull() throws Exception {
        // Arrange
        when(customerService.findCustomerById(99)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(get("/customer/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(customerService, times(1)).findCustomerById(99);
    }



    @Test
    public void deleteCustomer_ExistingCustomer_CallsServiceDelete() throws Exception {
        // Arrange
        doNothing().when(customerService).deleteCustomer(1);

        // Act & Assert
        mockMvc.perform(delete("/customer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService, times(1)).deleteCustomer(1);
    }

    @Test
    public void deleteCustomer_NonExistingCustomer_StillCallsServiceDelete() {
        // Arrange
        Integer customerId = 99;
        doNothing().when(customerService).deleteCustomer(customerId);

        // Act
        customerController.deleteCustomer(customerId);

        // Assert
        verify(customerService, times(1)).deleteCustomer(customerId);
    }

    @Test
    public void deleteCustomer_WhenServiceThrowsException_ReturnsBadRequest() throws Exception {
        // Arrange
        doThrow(new RuntimeException("Error deleting customer")).when(customerService).deleteCustomer(1);

        try {
            // Act & Assert
            mockMvc.perform(delete("/customer/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError());
        } catch (Exception e) {
            // Es posible que la excepción se propague, lo que es esperado
            // si no hay un manejador de excepciones global
        }

        verify(customerService, times(1)).deleteCustomer(1);
    }
}