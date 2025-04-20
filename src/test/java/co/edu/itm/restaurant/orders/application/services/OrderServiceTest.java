package co.edu.itm.restaurant.orders.application.services;

import co.edu.itm.restaurant.orders.application.dto.OrderDTO;
import co.edu.itm.restaurant.orders.application.dto.OrderItemDTO;
import co.edu.itm.restaurant.orders.domain.entities.Order;
import co.edu.itm.restaurant.orders.domain.repositories.OrderItemRepository;
import co.edu.itm.restaurant.orders.domain.services.OrderHandleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderHandleService orderHandleService;

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderService orderService;

    private OrderDTO testOrderDTO;
    private Order testOrder;
    private List<Order> testOrders;

    @BeforeEach
    void setUp() {
        // Configurar OrderDTO para pruebas
        testOrderDTO = new OrderDTO();
        testOrderDTO.setId(1);
        testOrderDTO.setCustomer(100);
        testOrderDTO.setComments("Test order");
        testOrderDTO.setOrderTotal(50.0);

        List<OrderItemDTO> items = new ArrayList<>();
        OrderItemDTO item1 = new OrderItemDTO();
        item1.setName("Pizza");
        item1.setAmount(2);
        item1.setUnitPrice(15.0);

        OrderItemDTO item2 = new OrderItemDTO();
        item2.setName("Soda");
        item2.setAmount(2);
        item2.setUnitPrice(10.0);

        items.add(item1);
        items.add(item2);
        testOrderDTO.setItems(items);

        // Configurar Order para pruebas
        testOrder = new Order();
        testOrder.setId(1);
        testOrder.setCustomer(100);
        testOrder.setComments("Test order");
        testOrder.setOrderTotal(50.0);
        testOrder.setItems(201);

        // Configurar lista de órdenes para pruebas
        Order order2 = new Order();
        order2.setId(2);
        order2.setCustomer(101);
        order2.setComments("Another test order");
        order2.setOrderTotal(75.0);
        order2.setItems(202);

        testOrders = Arrays.asList(testOrder, order2);
    }

    @Test
    void saveOrder_ShouldReturnSavedOrderDTO() {
        // Arrange
        when(orderHandleService.saveOrder(any(OrderDTO.class))).thenReturn(testOrderDTO);

        // Act
        OrderDTO savedOrderDTO = orderService.saveOrder(testOrderDTO);

        // Assert
        assertNotNull(savedOrderDTO);
        assertEquals(1, savedOrderDTO.getId());
        assertEquals(100, savedOrderDTO.getCustomer());
        assertEquals("Test order", savedOrderDTO.getComments());
        assertEquals(50.0, savedOrderDTO.getOrderTotal());
        assertEquals(2, savedOrderDTO.getItems().size());
        verify(orderHandleService, times(1)).saveOrder(testOrderDTO);
    }

    @Test
    void findAll_ShouldReturnAllOrders() {
        // Arrange
        when(orderHandleService.findAll()).thenReturn(testOrders);

        // Act
        List<Order> foundOrders = orderService.findAll();

        // Assert
        assertNotNull(foundOrders);
        assertEquals(2, foundOrders.size());
        assertEquals(1, foundOrders.get(0).getId());
        assertEquals(2, foundOrders.get(1).getId());
        verify(orderHandleService, times(1)).findAll();
    }

    @Test
    void findAll_WhenNoOrders_ShouldReturnEmptyList() {
        // Arrange
        when(orderHandleService.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Order> foundOrders = orderService.findAll();

        // Assert
        assertNotNull(foundOrders);
        assertTrue(foundOrders.isEmpty());
        verify(orderHandleService, times(1)).findAll();
    }

    @Test
    void findById_WhenOrderExists_ShouldReturnOrder() {
        // Arrange
        Integer orderId = 1;
        when(orderHandleService.findById(orderId)).thenReturn(testOrder);

        // Act
        Order foundOrder = orderService.findById(orderId);

        // Assert
        assertNotNull(foundOrder);
        assertEquals(1, foundOrder.getId());
        assertEquals(100, foundOrder.getCustomer());
        assertEquals("Test order", foundOrder.getComments());
        assertEquals(50.0, foundOrder.getOrderTotal());
        assertEquals(201, foundOrder.getItems());
        verify(orderHandleService, times(1)).findById(orderId);
    }

    @Test
    void findById_WhenOrderDoesNotExist_ShouldReturnNull() {
        // Arrange
        Integer orderId = 99;
        when(orderHandleService.findById(orderId)).thenReturn(null);

        // Act
        Order foundOrder = orderService.findById(orderId);

        // Assert
        assertNull(foundOrder);
        verify(orderHandleService, times(1)).findById(orderId);
    }

    @Test
    void saveOrder_WhenHandleServiceThrowsException_ShouldPropagateException() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        when(orderHandleService.saveOrder(any(OrderDTO.class)))
                .thenThrow(new RuntimeException("Error saving order"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            orderService.saveOrder(orderDTO);
        });

        assertEquals("Error saving order", exception.getMessage());
        verify(orderHandleService, times(1)).saveOrder(orderDTO);
    }
}