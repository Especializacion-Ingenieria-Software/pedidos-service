package co.edu.itm.restaurant.orders.domain.repositories;

import co.edu.itm.restaurant.orders.domain.entities.Customer;
import co.edu.itm.restaurant.orders.domain.entities.Order;
import co.edu.itm.restaurant.orders.infraestructure.persistence.IOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderRepositoryTest {

    @Mock
    private IOrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OrderRepository orderRepositoryUnderTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave_WhenCustomerExists_AssignsIdAndSavesOrder() {
        // Arrange
        int customerId = 123;

        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("John Doe");

        Order order = new Order();
        order.setCustomer(customerId);
        order.setOrderTotal(100.0);
        order.setComments("Test order");
        order.setItems(456);

        when(customerRepository.findById(customerId)).thenReturn(customer);
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Order savedOrder = orderRepositoryUnderTest.save(order);

        // Assert
        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
        assertEquals(customerId, savedOrder.getCustomer());
        assertEquals(100.0, savedOrder.getOrderTotal());
        assertEquals("Test order", savedOrder.getComments());
        assertEquals(456, savedOrder.getItems());
        verify(customerRepository, times(1)).findById(customerId);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testSave_WhenCustomerDoesNotExist_ReturnsNull() {
        // Arrange
        int customerId = 123;

        Order order = new Order();
        order.setCustomer(customerId);
        order.setOrderTotal(100.0);

        when(customerRepository.findById(customerId)).thenReturn(null);

        // Act
        Order savedOrder = orderRepositoryUnderTest.save(order);

        // Assert
        assertNull(savedOrder);
        verify(customerRepository, times(1)).findById(customerId);
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    public void testSave_WhenCustomerRepositoryThrowsException_ReturnsNull() {
        // Arrange
        int customerId = 123;

        Order order = new Order();
        order.setCustomer(customerId);

        when(customerRepository.findById(customerId)).thenThrow(new RuntimeException("Database error"));

        // Act
        Order savedOrder = orderRepositoryUnderTest.save(order);

        // Assert
        assertNull(savedOrder);
        verify(customerRepository, times(1)).findById(customerId);
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    public void testFindById_WhenOrderExists_ReturnsOrder() {
        // Arrange
        Integer orderId = 123;

        Order expectedOrder = new Order();
        expectedOrder.setId(orderId);
        expectedOrder.setCustomer(456);
        expectedOrder.setOrderTotal(200.0);
        expectedOrder.setComments("Sample order");
        expectedOrder.setItems(789);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(expectedOrder));

        // Act
        Order foundOrder = orderRepositoryUnderTest.findById(orderId);

        // Assert
        assertNotNull(foundOrder);
        assertEquals(orderId, foundOrder.getId());
        assertEquals(456, foundOrder.getCustomer());
        assertEquals(200.0, foundOrder.getOrderTotal());
        assertEquals("Sample order", foundOrder.getComments());
        assertEquals(789, foundOrder.getItems());
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    public void testFindById_WhenOrderDoesNotExist_ReturnsNull() {
        // Arrange
        Integer orderId = 123;

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act
        Order foundOrder = orderRepositoryUnderTest.findById(orderId);

        // Assert
        assertNull(foundOrder);
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    public void testFindAll_ReturnsAllOrders() {
        // Arrange
        Order order1 = new Order();
        order1.setId(1);
        order1.setCustomer(100);
        order1.setOrderTotal(150.0);
        order1.setComments("First order");
        order1.setItems(111);

        Order order2 = new Order();
        order2.setId(2);
        order2.setCustomer(200);
        order2.setOrderTotal(250.0);
        order2.setComments("Second order");
        order2.setItems(222);

        List<Order> expectedOrders = Arrays.asList(order1, order2);

        when(orderRepository.findAll()).thenReturn(expectedOrders);

        // Act
        List<Order> foundOrders = orderRepositoryUnderTest.findAll();

        // Assert
        assertNotNull(foundOrders);
        assertEquals(2, foundOrders.size());

        assertEquals(1, foundOrders.get(0).getId());
        assertEquals(100, foundOrders.get(0).getCustomer());
        assertEquals(150.0, foundOrders.get(0).getOrderTotal());
        assertEquals("First order", foundOrders.get(0).getComments());
        assertEquals(111, foundOrders.get(0).getItems());

        assertEquals(2, foundOrders.get(1).getId());
        assertEquals(200, foundOrders.get(1).getCustomer());
        assertEquals(250.0, foundOrders.get(1).getOrderTotal());
        assertEquals("Second order", foundOrders.get(1).getComments());
        assertEquals(222, foundOrders.get(1).getItems());

        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testFindAll_WhenNoOrders_ReturnsEmptyList() {
        // Arrange
        when(orderRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<Order> foundOrders = orderRepositoryUnderTest.findAll();

        // Assert
        assertNotNull(foundOrders);
        assertTrue(foundOrders.isEmpty());
        verify(orderRepository, times(1)).findAll();
    }
}