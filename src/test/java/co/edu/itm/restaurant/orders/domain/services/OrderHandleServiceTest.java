package co.edu.itm.restaurant.orders.domain.services;

import co.edu.itm.restaurant.orders.application.dto.OrderDTO;
import co.edu.itm.restaurant.orders.application.dto.OrderItemDTO;
import co.edu.itm.restaurant.orders.domain.entities.Order;
import co.edu.itm.restaurant.orders.domain.entities.OrderItem;
import co.edu.itm.restaurant.orders.domain.repositories.OrderItemRepository;
import co.edu.itm.restaurant.orders.domain.repositories.OrderRepository;
import co.edu.itm.restaurant.orders.domain.value_objects.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderHandleServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderHandleService orderHandleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveOrder_Success() {
        // Arrange
        OrderItemDTO orderItemDTO1 = new OrderItemDTO();
        orderItemDTO1.setName("Pizza");
        orderItemDTO1.setAmount(2);
        orderItemDTO1.setUnitPrice(10.5);

        OrderItemDTO orderItemDTO2 = new OrderItemDTO();
        orderItemDTO2.setName("Soda");
        orderItemDTO2.setAmount(1);
        orderItemDTO2.setUnitPrice(2.0);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomer(123);
        orderDTO.setComments("Test order");
        orderDTO.setOrderTotal(23.0);
        orderDTO.setItems(Arrays.asList(orderItemDTO1, orderItemDTO2));

        // Mock OrderItemRepository behavior
        when(orderItemRepository.save(any(OrderItem.class))).thenAnswer(invocation -> {
            OrderItem savedOrderItem = invocation.getArgument(0);
            savedOrderItem.setId(1001); // Simulate assigning ID
            return savedOrderItem;
        });

        // Mock OrderRepository behavior
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order savedOrder = invocation.getArgument(0);
            savedOrder.setId(2001); // Simulate assigning ID
            return savedOrder;
        });

        // Act
        OrderDTO result = orderHandleService.saveOrder(orderDTO);

        // Assert
        assertNotNull(result);
        assertEquals(2001, result.getId());
        assertEquals(123, result.getCustomer());
        assertEquals("Test order", result.getComments());
        assertEquals(23.0, result.getOrderTotal());

        // Verify OrderItemRepository.save was called
        ArgumentCaptor<OrderItem> orderItemCaptor = ArgumentCaptor.forClass(OrderItem.class);
        verify(orderItemRepository, times(1)).save(orderItemCaptor.capture());

        OrderItem capturedOrderItem = orderItemCaptor.getValue();
        assertNotNull(capturedOrderItem);
        assertNotNull(capturedOrderItem.getItem());
        assertEquals(2, capturedOrderItem.getItem().size());
        assertEquals("Pizza", capturedOrderItem.getItem().get(0).getName());
        assertEquals(2, capturedOrderItem.getItem().get(0).getAmount());
        assertEquals(10.5, capturedOrderItem.getItem().get(0).getUnitPrice());
        assertEquals("Soda", capturedOrderItem.getItem().get(1).getName());

        // Verify OrderRepository.save was called
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository, times(1)).save(orderCaptor.capture());

        Order capturedOrder = orderCaptor.getValue();
        assertNotNull(capturedOrder);
        assertEquals(123, capturedOrder.getCustomer());
        assertEquals("Test order", capturedOrder.getComments());
        assertEquals(23.0, capturedOrder.getOrderTotal());
        assertEquals(1001, capturedOrder.getItems());
    }

    @Test
    public void testSaveOrder_WithEmptyItems() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomer(123);
        orderDTO.setComments("Empty order");
        orderDTO.setOrderTotal(0.0);
        orderDTO.setItems(new ArrayList<>());

        // Mock OrderItemRepository behavior
        when(orderItemRepository.save(any(OrderItem.class))).thenAnswer(invocation -> {
            OrderItem savedOrderItem = invocation.getArgument(0);
            savedOrderItem.setId(1002);
            return savedOrderItem;
        });

        // Mock OrderRepository behavior
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order savedOrder = invocation.getArgument(0);
            savedOrder.setId(2002);
            return savedOrder;
        });

        // Act
        OrderDTO result = orderHandleService.saveOrder(orderDTO);

        // Assert
        assertNotNull(result);
        assertEquals(2002, result.getId());

        // Verify empty items were handled correctly
        ArgumentCaptor<OrderItem> orderItemCaptor = ArgumentCaptor.forClass(OrderItem.class);
        verify(orderItemRepository, times(1)).save(orderItemCaptor.capture());

        OrderItem capturedOrderItem = orderItemCaptor.getValue();
        assertNotNull(capturedOrderItem);
        assertTrue(capturedOrderItem.getItem().isEmpty());
    }

    @Test
    public void testFindById_WhenOrderExists() {
        // Arrange
        Integer orderId = 123;

        Order expectedOrder = new Order();
        expectedOrder.setId(orderId);
        expectedOrder.setCustomer(456);
        expectedOrder.setOrderTotal(100.0);
        expectedOrder.setComments("Test order");
        expectedOrder.setItems(789);

        when(orderRepository.findById(orderId)).thenReturn(expectedOrder);

        // Act
        Order result = orderHandleService.findById(orderId);

        // Assert
        assertNotNull(result);
        assertEquals(orderId, result.getId());
        assertEquals(456, result.getCustomer());
        assertEquals(100.0, result.getOrderTotal());
        assertEquals("Test order", result.getComments());
        assertEquals(789, result.getItems());
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    public void testFindById_WhenOrderDoesNotExist() {
        // Arrange
        Integer orderId = 123;
        when(orderRepository.findById(orderId)).thenReturn(null);

        // Act
        Order result = orderHandleService.findById(orderId);

        // Assert
        assertNull(result);
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    public void testFindAll_WhenOrdersExist() {
        // Arrange
        Order order1 = new Order();
        order1.setId(1);
        order1.setCustomer(100);

        Order order2 = new Order();
        order2.setId(2);
        order2.setCustomer(200);

        List<Order> expectedOrders = Arrays.asList(order1, order2);

        when(orderRepository.findAll()).thenReturn(expectedOrders);

        // Act
        List<Order> results = orderHandleService.findAll();

        // Assert
        assertNotNull(results);
        assertEquals(2, results.size());
        assertEquals(1, results.get(0).getId());
        assertEquals(2, results.get(1).getId());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testFindAll_WhenNoOrders() {
        // Arrange
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Order> results = orderHandleService.findAll();

        // Assert
        assertNotNull(results);
        assertTrue(results.isEmpty());
        verify(orderRepository, times(1)).findAll();
    }
}